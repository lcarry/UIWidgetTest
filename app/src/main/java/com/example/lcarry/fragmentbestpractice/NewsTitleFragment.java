package com.example.lcarry.fragmentbestpractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lcarry.uiwidgettest.R;

import java.util.List;

/**
 * Created by ${lcarry} on 2017/4/14.
 */

public class NewsTitleFragment extends Fragment {
    private boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag,container,false);

        RecyclerView newsTitleRecyclerView = (RecyclerView) view.
                findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(manager);
        NewsAdapter adapter = new NewsAdapter(News.getNews());
        newsTitleRecyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout) != null){
            //存在新闻内容layout,双页
            isTwoPane = true;
        } else {
            //不存在新闻内容layout，单页
            isTwoPane = false;
        }
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
        private List<News> mNewsList;

        class ViewHolder extends RecyclerView.ViewHolder{

            TextView newsTitleText;
//            TextView newsContentText;
            public ViewHolder(View itemView) {
                super(itemView);
                newsTitleText = (TextView)itemView.findViewById(R.id.news_title);
//                newsContentText = (TextView)itemView.findViewById(R.id.news_content);
            }
        }

        public NewsAdapter(List<News> list){
            mNewsList = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.news_item,parent,false);
            final ViewHolder holder = new ViewHolder(view);

            //item点击事件
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = mNewsList.get(holder.getAdapterPosition());
                    if (isTwoPane){
                        //双页模式则刷新NewsContentFragment中的内容
                        NewsContentFragment fragment =(NewsContentFragment)
                                getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        fragment.refresh(news.getTitle(),news.getContent());
                    } else {
                        //单页模式直接启动NewsContentActivity
                        NewsContentActivity.actionStart(v.getContext(),news.getTitle(),news.getContent());
                    }
                }
            });


            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            News news = mNewsList.get(position);
            holder.newsTitleText.setText(news.getTitle());
//            holder.newsContentText.setText(news.getContent());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }
}
