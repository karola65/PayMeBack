package com.example.paymeback;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import java.util.ArrayList;

/**
 * Main Activity for the List of Groups Layout.
 *
 * It handles the actions on the activity_all_groups_list layout
 * @see res.layout
 * @see GroupListAdapter
 * @see GroupItem
 *
 * @author Anna
 */
public class AllGroupList extends AppCompatActivity {
    /**
     * The RecyclerView where all the groups are going to be listed
     */
    private RecyclerView mRecyclerView;
    /**
     * The adapter to compute the group items in the RecyclerView
     * @see GroupListAdapter
     */
    private GroupListAdapter mAdapter;
    /**
     * The layout manager to position well the group items in the RecyclerView
     */
    private RecyclerView.LayoutManager mLayoutManager;
    /**
     * ImageView to Logout when clicking on it
     */
    private ImageView Logout;
    /**
     * memory of the users and tripgroups existing
     */
    private Memory mem;
    /**
     * ArrayList of the positions for the groupitems
     */
    private ArrayList<Integer> positions = new ArrayList<>();
    /**
     * List of the group items
     */
    private ArrayList<GroupItem> mGroupList;

    /**
     * On Create method when the activity is created.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setting the all_groups_list layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_groups_list);
        //getting the intent from the precedent activity
        Intent intent = getIntent();
        //getting the memory from the precedent activity
        mem = (Memory) intent.getSerializableExtra("mem");
        Logout = (ImageView) findViewById(R.id.avatar);

        //defining the logout image and setting its listener
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AllGroupList.this, Main2Activity.class);
                intent.putExtra("mem",mem);
                String currentXML = MainActivity.writeXml(mem);
                MainActivity.SaveXML(currentXML);
                startActivity(intent);

            }
        });

        createGroupList();
        buildRecyclerView();


    }

    /**
     * Converting a TripGroup in a GroupItem for the View
     * @param g
     * @return GroupItem
     */
    public GroupItem tripGrouptoGroupItem(TripGroup g) {
        String groupName = g.getName();
        ArrayList<User> user_list = g.getMembers();
        String groupItem_users = "Members: ";
        for (User u : user_list) {
            String user_firstname = u.userName;
            if (user_list.lastIndexOf(u) != user_list.size() - 1) {
                groupItem_users = groupItem_users + user_firstname + ", ";
            } else {
                groupItem_users = groupItem_users + user_firstname;
            }
        }
        GroupItem groupItem = new GroupItem(R.drawable.trip_image_resized, groupName, groupItem_users);
        return groupItem;
    }

    /**
     * Removing the item of the given position
     * @param position
     */
    public void removeItem(int position){
        mGroupList.remove(position);
        Intent intent = new Intent(AllGroupList.this, AllGroupList.class);
        intent.putExtra("mem",mem);
        String currentXML = MainActivity.writeXml(mem);
        MainActivity.SaveXML(currentXML);
        startActivity(intent);
        mAdapter.notifyItemRemoved(position);
    }

    /**
     * Setting the list of TripGroups from the memory (mGroupList)
     * and their position (positions)
     */
    public void createGroupList(){
        mGroupList = new ArrayList<>();
        int i = 0;
        for (TripGroup g : mem.getGroups()){
            if(g.isUserInGroup(mem.getMainuser())){
                mGroupList.add(tripGrouptoGroupItem(g));
                positions.add(i);
            }
            i++;
        }

    }
    /**
     * Building the RecyclerView with its Adapter and LayoutManager
     */
    public void buildRecyclerView(){
        //creating the RecyclerView
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        //creating its LayoutManager and Adapter
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new GroupListAdapter(mGroupList);
        //Setting the Layout Manager  and Adapter in the RecyclerView
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //Setting the Listeners when clicking on an item of the RecyclerView
        mAdapter.setOnItemClickListener(new GroupListAdapter.OnItemClickListener() {
            /**
             * When clicking on an item, passing an intent to the GroupView Activity,
             * and fining the position of the item clicked in the memomry
             * @see Memory
             * @see GroupView
             * @see com.example.paymeback.GroupListAdapter.OnItemClickListener
             * @param position
             */
            @Override
            public void onItemClick(int position) {
                //go to the group's page
                Intent intent = new Intent(AllGroupList.this, GroupView.class);
                mem.setOnegroup(positions.get(position));
                intent.putExtra("mem",mem);
                String currentXML = MainActivity.writeXml(mem);
                MainActivity.SaveXML(currentXML);
                startActivity(intent);

            }

            /**
             * When clicking on the trash image, delete the item from the view and
             * delete the group from the memory
             * @see Memory
             * @param position
             */
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
                mem.deleteGroup(positions.get(position));
                Intent intent = new Intent(AllGroupList.this, AllGroupList.class);
                intent.putExtra("mem",mem);
                String currentXML = MainActivity.writeXml(mem);
                MainActivity.SaveXML(currentXML);
                startActivity(intent);

            }

        });

    }

    /**
     * Creating a new group in the view and in the memory and coming to the GroupList Layout,
     * once the group is created in the CreateGroup Activity
     *
     * @see CreateGroup
     * @see Memory
     * @param view
     */
    public void dothis(View view){
        Intent intent = new Intent(AllGroupList.this, CreateGroup.class);
        intent.putExtra("mem",mem);
        String currentXML = MainActivity.writeXml(mem);
        MainActivity.SaveXML(currentXML);
        startActivity(intent);


    }

    /**
     * Going to the precedent page when clicking on Back default button,
     * updating the xml.
     * @see MainActivity
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent (AllGroupList.this, AllGroupList.class);
        intent.putExtra("mem", mem);
        String currentXML = MainActivity.writeXml(mem);
        MainActivity.SaveXML(currentXML);
        startActivity(intent);
    }
}