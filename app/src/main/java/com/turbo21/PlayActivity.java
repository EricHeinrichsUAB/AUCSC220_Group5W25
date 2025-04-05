package com.turbo21;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.animation.ObjectAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.animation.AnimatorSet;

public class PlayActivity extends AppCompatActivity {

    private ImageView chip5, chip10, chip25, chip50, chip100;
    private Button hitButton, standButton;
    public int bet;
    private GameManager GameManager = new GameManager();;
    private ConstraintLayout gameScreen; // We use ConstraintLayout bc max amount of cards in a hand is 11
    private ConstraintLayout cardsContainer; // Container to hold dynamically added ViewFlippers for cards


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_screen); // Set the play screen layout

        GameManager.StartGame();
        gameScreen = findViewById(R.id.gameScreen);

        // Initialize UI elements
        chip5 = findViewById(R.id.chip5);
        chip10 = findViewById(R.id.chip10);
        chip25 = findViewById(R.id.chip25);
        chip50 = findViewById(R.id.chip50);
        chip100 = findViewById(R.id.chip100);

        hitButton = findViewById(R.id.hitButton);
        standButton = findViewById(R.id.standButton);

        // initialize container for dynamically added cards
        cardsContainer = findViewById(R.id.cardsContainer);

        // selecting a chip (chip5 for now) to bet
        chip5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bet = 5;
                animateChip(chip5);
                animateChip(chip10);
                animateChip(chip25);
                animateChip(chip50);
                animateChip(chip100);
            }
        });

        // Hit and stand button functionality
        hitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameManager.DoHitButton();
                spawnNewCard(); // spawn a new card when player hits
            }
        });

        standButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameManager.DoStandButton();
            }
        });

    }//onCreate

    private void animateChip(ImageView chip) {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(chip, "translationX", 0f, 1000f);
        animator1.setDuration(1000);
        animator1.setInterpolator(new AccelerateDecelerateInterpolator());
        animator1.start();
    }

    private void spawnFlipperCard() {
        // create viewflipper for the card
        ViewFlipper viewFlipper = new ViewFlipper(PlayActivity.this);
        viewFlipper.setLayoutParams(new ConstraintLayout.LayoutParams(200, 300));

        // set constraints
        ConstraintLayout.LayoutParams flipperParams = (ConstraintLayout.LayoutParams) viewFlipper.getLayoutParams();
        flipperParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID; // Align with parent
        flipperParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID; // Align to the start of parent
        viewFlipper.setLayoutParams(flipperParams);

        //create fronts and backs for viewflipper card
        ImageView frontCard = new ImageView(PlayActivity.this);
        frontCard.setImageResource(R.drawable.clubs1);
        ImageView backCard = new ImageView(PlayActivity.this);
        backCard.setImageResource(R.drawable.back_of_card);

        // add imageviews to viewflipper
        viewFlipper.addView(frontCard);
        viewFlipper.addView(backCard);

        frontCard.setOnClickListener(v -> viewFlipper.showNext()); // flips card when clicked

        cardsContainer.addView(viewFlipper); //adds viewflipper to container

    }//spawnFlipperCard


    private void spawnNewCard() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.clubs1);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );

        // center the card horizontally and vertically (not centered vertically yet)
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;   // Align top to parent
        layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID; // Align bottom to parent
        layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID; // Align left to parent
        layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID; // Align right to parent

        imageView.setLayoutParams(layoutParams);
        cardsContainer.addView(imageView);
        addCardToDeck(imageView);
    }

    private void addCardToDeck(ImageView imageView) {
        // X and Y translations
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(imageView, "translationX", 200f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(imageView, "translationY", 200f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorX, animatorY); // plays both animations together
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

}
