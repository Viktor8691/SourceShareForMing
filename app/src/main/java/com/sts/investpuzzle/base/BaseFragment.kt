package com.sts.investpuzzle.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.sts.investpuzzle.core.navigation.INavigationService
import com.sts.investpuzzle.extensions.observe
import com.sts.investpuzzle.extensions.observeEvent
import java.lang.Exception
import java.lang.RuntimeException
import java.util.*
import javax.inject.Inject

abstract class BaseFragment <V : BaseViewModel<*>, VB : ViewBinding> : Fragment() , View.OnClickListener {
    @Inject
    lateinit var navigationService: INavigationService

    lateinit var viewModel: V
    val isViewModelInitialized : Boolean
        get() = this::viewModel.isInitialized
    private var viewBinding : VB? = null
    protected val viewBind get() = viewBinding!!
    protected fun showToast(message: String) { baseActivity?.showToast(message) }
    protected fun showError(title : String, message : String) { baseActivity?.showError(title, message) }
    protected open fun setUpAsync() {}

    val baseActivity : BaseActivity<*, *>?
        get() {
            return try {
                requireActivity() as BaseActivity<*, *>
            } catch (e : Exception){
                null
            }
        }

    fun setupViewBinding(viewBinding: VB){
        this.viewBinding = viewBinding
    }

    protected abstract fun setUp()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewBinding ==  null)
            throw  RuntimeException("viewBinding not initialized")

        setUp()
        initBaseModelObservers()
    }

    private fun initBaseModelObservers(){
        observeEvent(viewModel.toastMessage) {baseActivity?.showToast(it)}
        observeEvent(viewModel.progressVisible) { if (it) baseActivity?.showLoading() else baseActivity?.hideLoading() }
        observeEvent(viewModel.errorMessage){ showError(it.first, it.second) }
        observe(viewModel.asyncLoadDone) { if(it) setUpAsync() }
    }

    internal inline fun <reified T : ViewModel> withViewModel(body: T.() -> Unit): T {
        //For testing only
        return if (isViewModelInitialized) {
            (viewModel as T).body()
            viewModel as T
        } else {
            val vm = ViewModelProvider(this)[T::class.java]
            vm.takeIf { it is BaseViewModel<*> }?.let {
                @Suppress("UNCHECKED_CAST")
                viewModel = it as V
            } ?: throw UnknownFormatConversionException("View model not BaseViewModel")
            vm.body()
            vm
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    override fun onStop() {
        super.onStop()
        baseActivity?.hideLoading()
    }

    override fun onClick(v: View?) {}
}