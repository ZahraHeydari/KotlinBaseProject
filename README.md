# KotlinBaseProject
A base code which I almost use in every project I work on!
It shows how to use ViewModels and Room together with Coroutines & Koin by Clean Architecture in Kotlin!
This way you don`t need to repeat the same code in different parts of your project over & over.

### BaseActivity:
All activities can extends this abstract class.
- To handle the back process for all its child fragments
- To handle the navigation of fragments(add/replace)
- To inflate the view of activities using data binding util


### BaseFragment:
All fragments extend this abstract class.
- To inflate root view using data binding util
- To override a Toolbar for fragment using toolBarId if it exists
- To check the Authentication of user

### BaseViewGroup
It`s an interface that interacts with activities and fragments to determine ViewModel, LayoutId and ViewDataBinding of them

### BaseViewModel
Sometimes we have a same login in many parts of our application. Such as checking the internet connection,
showing/hiding progressBar when there is no data, or displaying a message/an error message when needed.
So we put all these cases in only one ViewModel as called BaseViewModel and all our ViewModels extend it.

### CommonToast
This class is used to customize toasts(appearance)

