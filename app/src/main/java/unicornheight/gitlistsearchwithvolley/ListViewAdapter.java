package unicornheight.gitlistsearchwithvolley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by deboajagbe on 9/19/17.
 */

public class ListViewAdapter extends ArrayAdapter<GitUser> {

    //the list that will be displayed
    private List<GitUser> gitList;

    //the context object
    private Context mCtx;

    //here we are getting the list and context
    //so while creating the object of this adapter class we need to give list and context
    public ListViewAdapter(List<GitUser> gitList, Context mCtx) {
        super(mCtx, R.layout.activity_main, gitList);
        this.gitList = gitList;
        this.mCtx = mCtx;
    }

    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.activity_main, null, true);

        //getting text views
        TextView textViewName = listViewItem.findViewById(R.id.git_name);
        TextView textViewImageUrl = listViewItem.findViewById(R.id.git_category);
        ImageView imageView = listViewItem.findViewById(R.id.git_image);

        //Getting the list for the specified position
        GitUser git = gitList.get(position);

        //setting list values to textviews
        textViewName.setText(git.getName());
        textViewImageUrl.setText(git.getGitUrl());
        Glide.with(mCtx).load(git.getAvatar()).crossFade().placeholder(R.mipmap.ic_launcher).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);

        //returning the listitem
        return listViewItem;
    }
}