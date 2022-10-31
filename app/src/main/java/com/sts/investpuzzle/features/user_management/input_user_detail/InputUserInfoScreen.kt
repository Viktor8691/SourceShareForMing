package com.sts.investpuzzle.features.user_management.input_user_detail

import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.sts.investpuzzle.R
import com.sts.investpuzzle.base.BaseFragment
import com.sts.investpuzzle.base.BaseViewModel
import com.sts.investpuzzle.core.data.network.model.accessories.Country
import com.sts.investpuzzle.core.data.network.model.accessories.Education
import com.sts.investpuzzle.core.data.network.model.accessories.Pronouns
import com.sts.investpuzzle.databinding.DialogGenderEducationBinding
import com.sts.investpuzzle.databinding.DialogSelectCountryBinding
import com.sts.investpuzzle.databinding.DialogYearPickerBinding
import com.sts.investpuzzle.databinding.ScreenInputUserInfoBinding
import com.sts.investpuzzle.extensions.RecyclerViewTouchListener
import com.sts.investpuzzle.extensions.observe
import com.sts.investpuzzle.extensions.observeEvent
import com.sts.investpuzzle.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputUserInfoScreen : BaseFragment<InputUserInfoScreenViewModel, ScreenInputUserInfoBinding>(){

    companion object {
        const val SCREEN_ARG = "input_user_info_screen"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        setupViewBinding(ScreenInputUserInfoBinding.inflate(inflater, container, false))
        withViewModel<InputUserInfoScreenViewModel> {
            observeEvent(localCountry) {
                viewBind.txvCountryName.text = it.name
                //GlideToVectorYou.init().with(context).load(Uri.parse(it.flag), viewBind.imvFlag)
            }
            observeEvent(isRegisterSuccess) {
                if (it) navigationService.openWelcomeSignupScreen()
            }
        }
        return viewBind.root
    }

    override fun setUp() {
        viewBind.imvSelectEducation.setOnClickListener(this)
        viewBind.imvSelectGender.setOnClickListener(this)
        viewBind.backButton.setOnClickListener(this)
        viewBind.imvSelectYearOfBirth.setOnClickListener(this)
        viewBind.txvSignUp.setOnClickListener(this)
        viewBind.imvSelectCountry.setOnClickListener(this)
    }



    private fun setYearOfBirth(yearOfBoth : String){
        viewBind.txvYearOfBirth.text = yearOfBoth
        viewModel.userInfoRequest.yearofBirth = yearOfBoth
    }

    private fun showYearPickerDialog(){
        val dialogViewBind = DialogYearPickerBinding.inflate(LayoutInflater.from(context), viewBind.root, false)
        val dialog = Utils.customDialog(requireContext(), dialogViewBind)
        dialogViewBind.numberPickerYear.maxValue = Utils.getMaxYearOfBirthday()
        dialogViewBind.numberPickerYear.minValue = 1990

        dialogViewBind.txvCancel.setOnClickListener { dialog.dismiss() }
        dialogViewBind.txvOkay.setOnClickListener {
            setYearOfBirth(dialogViewBind.numberPickerYear.value.toString())
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun setCountry(country: Country){
        viewBind.txvCountryName.text = country.name
        GlideToVectorYou.init().with(requireContext()).load(Uri.parse(country.flag), viewBind.imvFlag)
        viewModel.userInfoRequest.countryId = country.id
    }

    private fun showCountryListDialog(){
        val adapter = CountryListAdapter(requireContext(), BaseViewModel.accessoryData.countries.toMutableList())
        val dialogViewBind = DialogSelectCountryBinding.inflate(LayoutInflater.from(requireContext()), viewBind.root, false)
        val dialog = Utils.customDialog(requireContext(), dialogViewBind)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        dialogViewBind.rcvCountryList.layoutManager = layoutManager
        dialogViewBind.rcvCountryList.adapter = adapter
        dialogViewBind.rcvCountryList.addOnItemTouchListener(RecyclerViewTouchListener(requireContext(), dialogViewBind.rcvCountryList, object : RecyclerViewTouchListener.ClickListener {
            override fun onClick(view: View, position: Int) {
                setCountry(adapter.getItem(position))
                dialog.dismiss()
            }
        }))

        dialog.show()
    }

    private fun setEducation(education: Education){
        viewBind.txvEducation.text = education.title
        viewModel.userInfoRequest.educationId = education.id
    }

    private fun showEducationDialog(){
        val adapter = EducationListAdapter(BaseViewModel.accessoryData.educations.toMutableList())

        val dialogViewBind = DialogGenderEducationBinding.inflate(LayoutInflater.from(requireContext()), viewBind.root, false)
        val dialog = Utils.customDialog(requireContext(), dialogViewBind)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        dialogViewBind.rcvDataList.layoutManager = layoutManager
        dialogViewBind.rcvDataList.adapter = adapter
        dialogViewBind.rcvDataList.addOnItemTouchListener(RecyclerViewTouchListener(requireContext(), dialogViewBind.rcvDataList, object : RecyclerViewTouchListener.ClickListener {
            override fun onClick(view: View, position: Int) {
                if (position == adapter.itemCount - 1){
                    dialogViewBind.lytOtherContainer.visibility = View.VISIBLE
                    dialogViewBind.edtOther.hint = getString(R.string.type_your_education)
                }else {
                    setEducation(adapter.getItem(position))
                    dialog.dismiss()
                }

            }
        }))

        dialogViewBind.txvOkay.setOnClickListener {
            viewBind.txvEducation.text = dialogViewBind.edtOther.text
            viewModel.userInfoRequest.otherEducation = viewBind.txvEducation.text.toString()
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun setGender(pronouns: Pronouns){
        viewBind.txvGender.text = pronouns.title
        viewModel.userInfoRequest.pronounsId = pronouns.id
    }

    private fun showGenderDialog(){
        val adapter = GenderListAdapter(BaseViewModel.accessoryData.pronounses.toMutableList())
        val dialogViewBind = DialogGenderEducationBinding.inflate(LayoutInflater.from(requireContext()), viewBind.root, false)
        val dialog = Utils.customDialog(requireContext(), dialogViewBind)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        dialogViewBind.rcvDataList.layoutManager = layoutManager
        dialogViewBind.rcvDataList.adapter = adapter
        dialogViewBind.rcvDataList.addOnItemTouchListener(RecyclerViewTouchListener(requireContext(), dialogViewBind.rcvDataList, object : RecyclerViewTouchListener.ClickListener {
            override fun onClick(view: View, position: Int) {
                if (position == adapter.itemCount - 1) {
                    dialogViewBind.lytOtherContainer.visibility = View.VISIBLE
                    dialogViewBind.edtOther.hint = getString(R.string.type_your_gender)
                }else {
                    setGender(adapter.getItem(position))
                    dialog.dismiss()
                }
            }
        }))

        dialogViewBind.txvOkay.setOnClickListener {
            viewBind.txvGender.text = dialogViewBind.edtOther.text
            viewModel.userInfoRequest.otherPronouns = viewBind.txvGender.text.toString()
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.imvSelectGender -> {
                showGenderDialog()
            }
            R.id.backButton -> {
                navigationService.goBack()
            }
            R.id.imvSelectYearOfBirth -> {
                showYearPickerDialog()
            }
            R.id.imvSelectCountry -> {
                showCountryListDialog()
            }
            R.id.imvSelectEducation -> {
                showEducationDialog()
            }
            R.id.txvSignUp -> {
                if (viewBind.chbTerms.isChecked){
                    viewModel.uploadUserInfo()
                }else {
                    showError(getString(R.string.accept_terms_and_condition), getString(R.string.you_have_to_agree_terms_and_condition_to_use_wafflestock))
                }
            }
        }
    }
}