package com.android.kotlinbaseproject.presentation.base

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

abstract class BaseFragment<V : BaseViewModel, B : ViewDataBinding> : Fragment(),
    BaseViewGroup<V, B>,
    ToolbarManager,
    ProgressBarManager {

    val TAG: String = javaClass.name
    override lateinit var binding: B
    abstract var title: String
    abstract var menuId: Int
    var navigationListener: NavigationListener? = null
    var backCallback: MutableLiveData<OnBackPressedListener?>? = null
    override var toolbar: Toolbar? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initializeNavigationListener()
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        if (menuId > 0) {
            setHasOptionsMenu(true)
        }
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (toolBarId > 0) {
            toolbar = view.findViewById(toolBarId)
            (activity as AppCompatActivity).setSupportActionBar(toolbar)
            toolbar?.title = title
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel.progressBar = progressBar
        viewModel.commonMessage.observe(viewLifecycleOwner, Observer {
            it?.let { message ->
                showMessage(message)
            }
        })
        viewModel.hideProgressBar()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        if (menuId != 0) {
            inflater.inflate(menuId, menu)
        }
        return super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        arguments?.putBoolean(DATA_LOADED, viewModel.dataLoaded.get() == true)
    }

    override fun onDestroy() {
        if (backCallback != null && backCallback?.value != null) {
            backCallback?.value = null
        }
        super.onDestroy()
    }


    private fun initializeNavigationListener() {
        if (navigationListener != null)
            return

        if (activity is BaseActivity<*, *>) {
            navigationListener = activity as BaseActivity<*, *>
        }
    }


    /*fun onRequestError(errorModel: ErrorModel) {
        when (errorModel.errorStatus) {
            ErrorModel.ErrorStatus.UNAUTHORIZED-> {
                authenticationError()
            }
        }
    }*/


   /*fun authenticationError(callback: ((Boolean) -> Unit)? = null) {
        val login = LoginDialog.newInstance()

        login.loginListener = object : LoginResultListener {
            override fun onResult(status: Boolean) {
                if (status) {
                    if (callback != null) {
                        callback(status)
                    }
                }
            }
        }
        login.show(activity?.supportFragmentManager?.beginTransaction(), LoginDialog.CLASS_NAME)
    }*/

    fun showMessage(message: String, duration: Int = CommonToast.LENGTH_LONG) {
        context?.let {
            CommonToast.makeToast(it, message, duration).show()
        }
    }

    companion object {
        const val DATA_LOADED = "DATA-LOADED"
    }

}