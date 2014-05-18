package csoft.ro.Transsist;
import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.SimpleExpandableListAdapter;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: marius
 * Date: 1/5/14
 * Time: 5:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrarStatiiAutobuz extends  ExpandableListActivity  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statiibuz);
         ExpandableListView explvlist;
       //final  String[]tiglina3={"05.15","05.45", "06.15","06.45", "07.15","07.24", "07.45", "07.52" ,"08.15", "08.24" ,"08.45","08.52","09.15","09.45","10.15" ,"10.45","11.15", "11.45",
            //    "12.15", "12.45","13.15","13.45","14.15","14.45","15.15","15.45","16.15","16.45","17.15","17.45","18.15","18.45","19.15","19.45",
             //   "20.15","20.45","21.15","21.45","22.15"};



        explvlist = (ExpandableListView) findViewById(android.R.id.list);
        explvlist.setAdapter(new ParentLevel());

    }

    public class ParentLevel extends BaseExpandableListAdapter {

        @Override
        public Object getChild(int arg0, int arg1) {
            return arg1;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            CustExpListview SecondLevelexplv = new CustExpListview(OrarStatiiAutobuz.this);
            SecondLevelexplv.setAdapter(new SecondLevelAdapter());
            SecondLevelexplv.setGroupIndicator(null);
            return SecondLevelexplv;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 2;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupPosition;
        }

        @Override
        public int getGroupCount() {
            return 2;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            TextView tv = new TextView(OrarStatiiAutobuz.this);
            ImageView iv=new ImageView(OrarStatiiAutobuz.this);

            iv.setImageDrawable(getResources().getDrawable(R.drawable.button));
            iv.setPadding(0,5,250,5);
            return iv;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }




    }

    public class CustExpListview extends ExpandableListView {

        int intGroupPosition, intChildPosition, intGroupid;

        public CustExpListview(Context context) {
            super(context);
        }

        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(20000,
                    MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(15000,
                    MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public class SecondLevelAdapter extends BaseExpandableListAdapter {

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            TextView tv = new TextView(OrarStatiiAutobuz.this);
            tv.setText("child");
            tv.setLayoutParams(new ListView.LayoutParams(
                    FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT));

            return tv;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 5;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupPosition;
        }

        @Override
        public int getGroupCount() {
            return 1;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public  View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            TextView tv = new TextView(OrarStatiiAutobuz.this);
             TextView tv1=new TextView(OrarStatiiAutobuz.this);
           if(groupPosition==0)
               tv.setText("tur");
          if(groupPosition==1)
           tv1.setText("retur");

            return tv;

        }


        @Override
        public boolean hasStableIds() {
            // TODO Auto-generated method stub
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            // TODO Auto-generated method stub
            return true;
        }

    }


    int c1=0;

    double primaop;
    double ultimaop;

    String [] tiglina3 = new String[]{"05.15","05.45", "06.15","06.45", "07.15","07.24", "07.45", "07.52" ,"08.15", "08.24" ,"08.45","08.52","09.15","09.45","10.15" ,"10.45","11.15", "11.45",
            "12.15", "12.45","13.15","13.45","14.15","14.45","15.15","15.45","16.15","16.45","17.15","17.45","18.15","18.45","19.15","19.45",
            "20.15","20.45","21.15","21.45","22.15"};




    public void orar(String[] schedule,String statie){
        c1=0;
        String orfinal="0.0";
        String timp;
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minute=  Calendar.getInstance().get(Calendar.MINUTE);
        String ora = Integer.toString(hour);
        String minut=Integer.toString(minute);
        if(minute<10)
        {   timp=ora+"."+"0"+minut; }
        else
        { timp=ora+"."+minut; }


        double timpulprezent=Double.parseDouble(timp.toString());


        Arrays.sort(schedule, Collections.reverseOrder());
        System.out.println(timpulprezent)  ;
        for(int i = 0; i <schedule.length; i++){
            if(i+1>=schedule.length)   {
                break;
            }
            else{
                int n=schedule.length;
            double timpuldinvect=Double.parseDouble(schedule[i].toString());

            double timpuldinvect2=Double.parseDouble(schedule[i+1].toString());

             primaop=Double.parseDouble(schedule[n-1]);
            ultimaop= Double.parseDouble(schedule[0]) ;

            if(timpulprezent<=timpuldinvect&&(timpulprezent>timpuldinvect2)&&timpulprezent<=ultimaop&&timpulprezent>=primaop)
            {
                double c=timpuldinvect-timpulprezent;
                System.out.println(timpulprezent);
                        System.out.println(timpuldinvect);
                System.out.println(timpuldinvect2);

                if(c>=0.50)
                    c=c-0.40;
                c=c*100;
                if(c<0)
                    c=c*-1;
                     c1=(int)(Math.round(c));
                if(c1!=0)
                 orfinal=Integer.toString(c1);
                System.out.println(c);
            }
            }
        }
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(statie);
      //  System.out.println(timpulprezent);
        System.out.println(orfinal);

        if(timpulprezent>ultimaop||timpulprezent<primaop&&c1==0){
            alertDialog.setMessage("Programul s-a terminat");
        }
        else{
        if(orfinal.equals("1")&&c1==1){
            alertDialog.setMessage("Autobuzul va ajunge imediat ");
        }
            else{
     if(c1==0&&timpulprezent<=ultimaop&&timpulprezent>=primaop&&orfinal.equals("0.0"))
         alertDialog.setMessage("Autobuzul a ajuns");
        else
     if(c1!=0&&timpulprezent<=ultimaop&&timpulprezent>=primaop&&!(orfinal.equals("0.1")))
        alertDialog.setMessage("Autobuzul va ajunge in "+orfinal+" minute");
        }
        }
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
// Set the Icon for the Dialog
     //   alertDialog.setIcon(R.drawable.icon);
        alertDialog.show();
    }
}