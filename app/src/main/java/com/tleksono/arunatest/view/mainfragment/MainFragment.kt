package com.tleksono.arunatest.view.mainfragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tleksono.arunatest.R
import com.tleksono.arunatest.data.source.remote.State
import com.tleksono.arunatest.domain.model.Post
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.time.Duration
import javax.inject.Inject

/**
 * Created by trileksono on 07/12/20
 */
class MainFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainFragmentAdapter: MainFragmentAdapter

    private val mainFragmentViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        recycler_main.run {
            layoutManager = LinearLayoutManager(context)
            mainFragmentAdapter = MainFragmentAdapter(mutableListOf())

            adapter = mainFragmentAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        mainFragmentViewModel.fetchData()

        mainFragmentViewModel.run {
            postsLiveData.observe(viewLifecycleOwner, { consumeState(it) })
            queryStringLiveData.observe(viewLifecycleOwner, { consumeState(it) })
        }
    }


    @ExperimentalCoroutinesApi
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        menu.findItem(R.id.search)
            .setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                    val searchView = p0!!.actionView as SearchView
                    searchView.queryHint = "Search"
                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean = false

                        override fun onQueryTextChange(newText: String?): Boolean {
                            mainFragmentViewModel.searchQuery(newText.orEmpty())
                            return true
                        }
                    })
                    return true
                }

                override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                    return true
                }
            })
        super.onCreateOptionsMenu(menu, inflater)
    }


    private fun consumeState(state: State<List<Post>>) {
        when (state) {
            is State.Success -> {
                showContent(true)
                mainFragmentAdapter.refreshItem(state.data)
            }
            is State.Failure -> {
                Toast.makeText(context,state.error, Toast.LENGTH_SHORT).show()
                showContent(true)
            }
            is State.Loading -> showContent(false)
        }
    }

    private fun showContent(isShowing: Boolean) {
        if (isShowing) {
            recycler_main.isVisible = true
            progress_circular.isVisible = false
        } else {
            recycler_main.isVisible = false
            progress_circular.isVisible = true
        }
    }
}
