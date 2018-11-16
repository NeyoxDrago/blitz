package kmv.com.blitz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Nirnay Mittal on 10-09-2018.
 */

public class MySurface extends SurfaceView {
    private boolean playiing  = true;

    public MySurface(Context context) {
        super(context);
        new Anim().start();
    }

    private class Anim extends Thread
    {

        @Override
        public void run() {
            super.run();

            long last_updated_time = 0;
            long delay =100;
            int counter =0;
            int ids[] = { R.drawable.oneee ,
                    R.drawable.twoo,
                    R.drawable.threee,
                    R.drawable.fourr,
                    R.drawable.fivee,
                    R.drawable.six ,
                    R.drawable.seven ,
                    R.drawable.eight ,
                    R.drawable.nine ,
                    R.drawable.ten ,
                    R.drawable.eleven ,
                    R.drawable.twelve,
                    R.drawable.thirteen,
                    R.drawable.fourteen,
                    R.drawable.fifteen,
                    R.drawable.sixteen ,
                    R.drawable.seventeen ,
                    R.drawable.eighteen,
                    R.drawable.nineteen ,
                    R.drawable.twenty ,
                    R.drawable.twentyone ,
                    R.drawable.twentytwo ,
                    R.drawable.twentythree ,
                    R.drawable.twentyfour ,
                    R.drawable.twentyfive ,
                    R.drawable.twentysix ,
                    R.drawable.twentyseven ,
                    R.drawable.twentyeight ,
                    R.drawable.twentynine ,
                    R.drawable.thirty ,
                    R.drawable.thirtyone,
                    R.drawable.thirtytwo ,
                    R.drawable.thirtythree ,
                    R.drawable.thirtyfour ,
                    R.drawable.thirtyfive ,
                    R.drawable.thirtysix ,
                    R.drawable.thirtyseven ,
                    R.drawable.thirtyeight
            };

            while (true)
        {
            if(playiing)
            {
                long current_time = System.currentTimeMillis();

                if(current_time > last_updated_time + delay)
                {
                    if(counter>=30){
                        counter =0;
                    }
                    draw(ids[counter]);
                    last_updated_time = current_time;
                    counter++;
                }
            }

        }

        }

        public void draw(int long_ids)
        {
            SurfaceHolder holder = getHolder();
            Canvas canvas = holder.lockCanvas();

            if(canvas!=null)
            {
                canvas.drawColor(Color.WHITE);
                Paint p = new Paint();
                Bitmap bit = BitmapFactory.decodeResource(getContext().getResources(),long_ids);
                //bit = Bitmap.createScaledBitmap(bit , 1000 ,1000 ,true);
                canvas.drawBitmap(bit,50,50,p);
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }
 }
