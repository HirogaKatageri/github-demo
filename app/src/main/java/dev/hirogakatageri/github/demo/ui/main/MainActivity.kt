package dev.hirogakatageri.github.demo.ui.main

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dev.hirogakatageri.github.demo.R

import dev.hirogakatageri.github.demo.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val epoxyController = MainActivityEpoxyController()
    private val viewModel: MainViewModel by viewModel()
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.let { b -> setContentView(b.root) }
        setupView()

        lifecycleScope.launch {
            observeState()
        }
    }

    private fun setupView() = binding?.apply {
        txtUserSearch.setOnEditorActionListener(searchActionListener)

        usersRecyclerView.layoutManager =
            LinearLayoutManager(root.context, LinearLayoutManager.VERTICAL, false)
        usersRecyclerView.setController(epoxyController)
    }

    private suspend fun observeState() {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.state.collectLatest { state ->
                when (state) {
                    MainActivityState.Error -> onError()
                    is MainActivityState.Ready -> {
                        epoxyController.setUsers(state.data)
                        epoxyController.requestModelBuild()
                    }
                    MainActivityState.Searching -> Unit
                }
            }
        }
    }

    private fun onError() {
        binding?.let { b ->
            Snackbar.make(b.root, getString(R.string.error_user_query), Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private val searchActionListener = OnEditorActionListener { v, actionId, event ->
        when (actionId) {
            EditorInfo.IME_ACTION_DONE -> viewModel.search(v.text?.toString())
        }

        true
    }

}