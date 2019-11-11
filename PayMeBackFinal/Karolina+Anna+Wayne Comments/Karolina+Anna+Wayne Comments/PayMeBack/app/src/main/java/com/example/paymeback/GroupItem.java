package com.example.paymeback;

/**
 * GroupItem is the CardView layout for each item of the RecyclerView in anna_groups layout.
 * @see GroupListAdapter
 * @see AllGroupList
 *
 * @author Anna
 */

public class GroupItem {
    private int mImageResource;
    private String mGroupName;
    private String mGroupMembers;

    /**
     * Constructor with the delete image, trip name and trip members3
     * @param imageResource
     * @param groupName
     * @param groupMembers
     */
    public GroupItem(int imageResource, String groupName, String groupMembers) {
        mImageResource = imageResource;
        mGroupName = groupName;
        mGroupMembers = groupMembers;
    }



    //Getters

    /**
     * Getter for the delete image
     * @return delete image
     */
    public int getmImageResource() {
        return mImageResource;
    }

    /** Getter for the group name
     * @return group name
     */
    public String getmGroupName() {
        return mGroupName;
    }

    /** Getter for the group members
     * @return group members as a String
     */
    public String getmGroupMembers() {
        return mGroupMembers;
    }
}

