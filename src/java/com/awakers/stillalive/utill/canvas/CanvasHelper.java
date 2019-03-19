package com.awakers.stillalive.utill.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.awakers.stillalive.R;
import com.awakers.stillalive.app.AwakersApplication;

public class CanvasHelper extends View {
    Canvas canvas;
    public ImageView image;
    private Context mContext;
    private String mMission;
    private String mText;
    private Paint paint = new Paint();

    public CanvasHelper(Context context, Canvas canvas) {
        super(context);
        this.mContext = context;
        this.paint.setColor(-1);
        this.paint.setStyle(Style.FILL);
        this.canvas = canvas;
        this.image = new ImageView(this.mContext);
    }

    public void onDraw(Canvas canvas) {
        drawTextOnCanvas();
        super.onDraw(canvas);
    }

    public void drawTextOnCanvas() {
        this.canvas.drawPaint(this.paint);
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        linearLayout.setOrientation(1);
        linearLayout.setBackground(AwakersApplication.appComponent.app().getResources().getDrawable(R.drawable.background));
        linearLayout.setWeightSum(26.0f);
        linearLayout.setGravity(17);
        setWeight(7.0f, this.image);
        TextView textView = new TextView(this.mContext);
        setWeight(10.0f, textView);
        TextView textView2 = new TextView(this.mContext);
        setWeight(8.0f, textView2);
        linearLayout.addView(this.image);
        linearLayout.addView(textView);
        linearLayout.addView(textView2);
        textView.setText(this.mMission);
        textView.setTypeface(Typeface.createFromAsset(this.mContext.getAssets(), "font/font.ttf"));
        textView.setTextSize(2, 15.0f);
        textView.setDrawingCacheEnabled(true);
        textView.setPadding(40, 40, 40, 40);
        textView.setLineSpacing(2.0f, 1.5f);
        textView.setTextColor(Color.parseColor("#000000"));
        textView.setGravity(17);
        textView.measure(MeasureSpec.makeMeasureSpec(this.canvas.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(this.canvas.getHeight(), 1073741824));
        textView2.setText(this.mText);
        textView2.setTypeface(Typeface.createFromAsset(this.mContext.getAssets(), "font/font.ttf"));
        textView2.setTextSize(2, 18.0f);
        textView2.setDrawingCacheEnabled(true);
        textView2.setLineSpacing(2.0f, 1.5f);
        textView2.setPadding(20, 20, 20, 20);
        textView2.setTextColor(Color.parseColor("#000000"));
        textView2.setGravity(8388611);
        textView2.measure(MeasureSpec.makeMeasureSpec(this.canvas.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(this.canvas.getHeight(), 1073741824));
        linearLayout.setDrawingCacheEnabled(true);
        linearLayout.measure(MeasureSpec.makeMeasureSpec(this.canvas.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(this.canvas.getHeight(), 1073741824));
        linearLayout.layout(0, 0, linearLayout.getMeasuredWidth(), linearLayout.getMeasuredHeight());
        this.canvas.drawBitmap(linearLayout.getDrawingCache(), 0.0f, 0.0f, this.paint);
        linearLayout.removeAllViews();
    }

    public void setText(String str) {
        this.mText = str;
    }

    public ImageView getImage() {
        return this.image;
    }

    public void setImage(ImageView imageView) {
        this.image = imageView;
        imageView.measure(MeasureSpec.makeMeasureSpec(this.canvas.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(this.canvas.getHeight(), 1073741824));
    }

    public void setMission(String str) {
        this.mMission = str;
    }

    private <V extends View> void setWeight(float f, V v) {
        LayoutParams layoutParams = new LayoutParams(-2, -1);
        layoutParams.weight = f;
        v.setLayoutParams(layoutParams);
    }
}
