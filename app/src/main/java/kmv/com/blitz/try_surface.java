package kmv.com.blitz;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;

import su.levenetc.android.textsurface.Text;
import su.levenetc.android.textsurface.TextBuilder;
import su.levenetc.android.textsurface.TextSurface;
import su.levenetc.android.textsurface.animations.Alpha;
import su.levenetc.android.textsurface.animations.ChangeColor;
import su.levenetc.android.textsurface.animations.Circle;
import su.levenetc.android.textsurface.animations.Delay;
import su.levenetc.android.textsurface.animations.Parallel;
import su.levenetc.android.textsurface.animations.Rotate3D;
import su.levenetc.android.textsurface.animations.Sequential;
import su.levenetc.android.textsurface.animations.ShapeReveal;
import su.levenetc.android.textsurface.animations.SideCut;
import su.levenetc.android.textsurface.animations.Slide;
import su.levenetc.android.textsurface.animations.TransSurface;
import su.levenetc.android.textsurface.contants.Align;
import su.levenetc.android.textsurface.contants.Direction;
import su.levenetc.android.textsurface.contants.Pivot;
import su.levenetc.android.textsurface.contants.Side;

/**
 * Created by Nirnay Mittal on 11-09-2018.
 */

public class try_surface {

    public static void play(TextSurface textsurface)
    {
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        Text textDaai = TextBuilder
                .create("Welcome")
                .setPaint(paint)
                .setSize(64)
                .setAlpha(0)
                .setColor(Color.WHITE)
                .setPosition(Align.SURFACE_CENTER).build();

        Text textBraAnies = TextBuilder
                .create("To the ")
                .setPaint(paint)
                .setSize(30)
                .setAlpha(0)
                .setColor(Color.RED)
                .setPosition(Align.BOTTOM_OF, textDaai).build();

        Text textFokkenGamBra = TextBuilder
                .create("Computer Society ")
                .setPaint(paint)
                .setSize(37)
                .setAlpha(0)
                .setColor(Color.BLUE)
                .setPosition(Align.RIGHT_OF, textBraAnies).build();

        Text textHaai = TextBuilder
                .create("of")
                .setPaint(paint)
                .setSize(40)
                .setAlpha(0)
                .setColor(Color.RED)
                .setPosition(Align.BOTTOM_OF, textFokkenGamBra).build();

        Text textDaaiAnies = TextBuilder
                .create("Keshav Mahavidyalaya")
                .setPaint(paint)
                .setSize(34)
                .setAlpha(0)
                .setColor(Color.WHITE)
                .setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textHaai).build();

        Text texThyLamInnie = TextBuilder
                .create("BLITZ")
                .setPaint(paint)
                .setSize(64)
                .setAlpha(0)
                .setColor(Color.WHITE)
                .setPosition(Align.RIGHT_OF, textDaaiAnies).build();

        Text textThrowDamn = TextBuilder
                .create("BriLliant ")
                .setPaint(paint)
                .setSize(64)
                .setAlpha(0)
                .setColor(Color.RED)
                .setPosition(Align.BOTTOM_OF | Align.CENTER_OF, texThyLamInnie).build();

        Text textDevilishGang = TextBuilder
                .create("Information Technology")
                .setPaint(paint)
                .setSize(34)
                .setAlpha(0)
                .setColor(Color.RED)
                .setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textThrowDamn).build();

        Text zeolot = TextBuilder
                .create("Zeolots")
                .setPaint(paint)
                .setSize(44)
                .setAlpha(0)
                .setColor(Color.RED)
                .setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textDevilishGang).build();


        textsurface.play(
                new Sequential(
                        ShapeReveal.create(textDaai, 750, SideCut.show(Side.LEFT), false),
                        new Parallel(ShapeReveal.create(textDaai, 600, SideCut.hide(Side.LEFT), false), new Sequential(Delay.duration(300), ShapeReveal.create(textDaai, 600, SideCut.show(Side.LEFT), false))),
                        new Parallel(new TransSurface(500, textBraAnies, Pivot.CENTER), ShapeReveal.create(textBraAnies, 1300, SideCut.show(Side.LEFT), false)),
                        Delay.duration(500),
                        new Parallel(new TransSurface(750, textFokkenGamBra, Pivot.CENTER), Slide.showFrom(Side.LEFT, textFokkenGamBra, 750), ChangeColor.to(textFokkenGamBra, 750, Color.WHITE)),
                        Delay.duration(500),
                        new Parallel(TransSurface.toCenter(textHaai, 500), Rotate3D.showFromSide(textHaai, 750, Pivot.TOP)),
                        new Parallel(TransSurface.toCenter(textDaaiAnies, 500), Slide.showFrom(Side.TOP, textDaaiAnies, 500)),
                        new Parallel(TransSurface.toCenter(texThyLamInnie, 750), Slide.showFrom(Side.LEFT, texThyLamInnie, 500)),
                        Delay.duration(500),
                        new Parallel(
                                new Sequential(
                                        new Sequential(ShapeReveal.create(textThrowDamn, 500, Circle.show(Side.CENTER, Direction.OUT), false)),
                                        new Sequential(ShapeReveal.create(textDevilishGang, 500, Circle.show(Side.CENTER, Direction.OUT), false)),
                                        new Sequential(ShapeReveal.create(zeolot, 500, Circle.show(Side.CENTER, Direction.OUT), false))
                                )
                        ),
                        Delay.duration(200),
                        new Parallel(
                                ShapeReveal.create(textThrowDamn, 1500, SideCut.hide(Side.LEFT), true),
                                new Sequential(Delay.duration(250), ShapeReveal.create(textDevilishGang, 1500, SideCut.hide(Side.LEFT), true)),
                                new Sequential(ShapeReveal.create(zeolot, 500, Circle.show(Side.CENTER, Direction.OUT), false)),
                                Alpha.hide(texThyLamInnie, 1500) ,
                                Alpha.hide(zeolot, 1500),
                                Alpha.hide(textThrowDamn, 1500) ,
                                Alpha.hide(textDaaiAnies, 1500) ,
                                Alpha.hide(textDaai, 1500) ,
                                Alpha.hide(textFokkenGamBra, 1500)
                        )
                ));
    }


}

