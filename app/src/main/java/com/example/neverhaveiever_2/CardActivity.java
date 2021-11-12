package com.example.neverhaveiever_2;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;

@Layout(R.layout.card_view)
public class CardActivity {

    @View(R.id.txtAction)
    private TextView txtAction;

    @View(R.id.txtNumberAction)
    private TextView txtNumberAction;

    @View(R.id.btnNext)
    private Button btnNext;


    private CardItem cardItem;
    private Context context;
    private SwipePlaceHolderView swipeHolder;

    public CardActivity(Context context, CardItem cardItem, SwipePlaceHolderView swipeHolder ){
        this.context = context;
        this.cardItem = cardItem;
        this.swipeHolder = swipeHolder;

    }
    @Resolve
    private void onResolved(){
        if(cardItem != null){
            txtNumberAction.setText(cardItem.getCountAction());
            txtAction.setText(cardItem.getAction());
        }
    }

    @SwipeOut
    private void onSwipedOut(){
        Log.d("EVENT", "onSwipedOut");
        swipeHolder.addView(this);
    }








}
