package depromeet.depton.nunayun.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    protected val disposable = CompositeDisposable()
    lateinit var binding: T
    abstract val resourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, resourceId)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}