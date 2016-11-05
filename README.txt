
Fri Nov  4 13:26:58 PDT 2016

Keywords: Android array adapter multiple-view-type object Java recycle viewholder

This app is an example of a listview adapter that holds multiple viewtypes
using an objects.  It creates multiple types of viewholders based on
the object in the view addapter's item list.

The list adapter class is MultiItemListAdapter.  Two items that the
adapter must override to support for support of multiple views are:

* int getItemViewType(int position):  This method returns a 0-based integer
    index that indicates what view type the object uses.
* int getViewTypeCount(): This method returns a count of the total number
    of different views in the adapter's item list.

The adapter getView method is where the views displayed in the adapter's
listview are created.  With most of the ViewAdapters I've seen, the 
item's viewHolder (used to cache the views during recycling) is containde
as the getView's convertView tag.  The developer's code uses this convertView
to determine when the listview item's view must be updated.  If the
convertView is null or missing a viewHolder (i.e. convertView.getTag == null)
or the viewHolder represents a different view type or position than
the position getView is updating, you must update/create the convertView.

The 'expensive' part of the view adapter is creating a new view and finding
(findViewById) the various objects within the view.  A viewHolder is a
cache object that speeds up this process.  It contains the parent view
and the various object views within it.  This allows the view to be reused
as long as the view holder object is the same as the object being 
displayed in the listView item.

In the case of my code, this happens in the object setupVH method which
expects an inflater (used if creating a new view), a viewHolder object
(which may be null) and the position in the adapter's listView that is
being updated.

============
The example application:

This application implements 200 instances of 3 different view types 
adds them to an ArrayList and creates an instance of a listview
addapter to display them.

The three view child classes are Separator.java, Item.java and
ItemButton.java and associated R.layout xml files: separator.xml,
item.xml and button_item.xml.  All are fairly simple objects.
Two consist of only a TextView Object and the third contains
only a Button Object.

============
How the code works:

The parent class for the various other view holder classes is 
the abstract ViewHolderDelegate which contains a ViewHolder abstract
inner class.  All the actual view structures blong to the ViewHolder
child inner classs and the "knowledge" of how to update the view holder
objects is in their outter/wrapper classes.

The fact that all user objects displayed by the list item adapter
are children of the ViewHolderDelegate allows us to put them in the
array list which is passed to the list adapter.

The ViewHolderDelegate contains three methods and a constructor and 
two abstract classes the child classes are responsible for implementing.
It also contains a static method that supplies the view type to the
adapter getItemViewType method.

* ViewHolderDelegate is the class constructor and is called by the 
constructor for each child class.  We keep track of the resource id
used in each child class and this determines the view type for the child
class.

* int getItemViewType supplies the view type to the list view adapter.

* static int getViewTypeCount supplies the total number of view types
to the view adapter.

* abstract public int getItemResId supplies the resource id used by
the child class and is used to determine that the chilc view holder and
the child class are compatible.

* abstract protected View setupVH creates and updates the view holder
and its associated view.

Each child ViewHoldedDelegate, child ViewHolder and view is assigned
a unique index value used as an id mostly for debugging.  And each has
an associated getId method.

Each child also has an abstract toString method for debugging.

The ViewHolder inner class contains the View objects used to create
object view.  I made an effort to keep all the view objects -- even
it's position -- within the ViewHolder for purposes of encapulation.
The ViewHolderDelegate child setupVH method directly accesses the
ViewHolder view objects as a "trusted object" and to speed-up and simplify
ViewHolder update.

The ViewHolderDelegate.ViewHolder class has a constructor, a getId and
two abstract methods: getPosition and getItemResId.

* abstract public int getItemResId: the outer ViewHolderDelegate and the
inner ViewHolder classes use the resId to determine that they are 
compatible.  The ViewHolderDelegate setupVH method uses it to determine
whether it needs to create a new ViewHolder object.

* abstract int getPosition is used by the adapter getView method to determine
if a viewholder object needs to be updated.

===============
Adding a new object:

Hopefully, creating a new object is fairly straight forward.  Simply create
the R.layout.xml file for your new view object, copy one of the example
ViewHolderDelegate child classes (Item.java, Separator.java or
ButtonItem.java) and update it to support your new object.  Updating
the new child consists of updating the resId R.layout value, updating
the ViewHolder child object with the new view objects, and updating
the ViewHolder child setupVH method to appropriately update/create
the VeiwHolder.
