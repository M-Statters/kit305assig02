package au.edu.utas.username.kit305assig02;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RaffleDetails extends AppCompatActivity
{
        private static final String TAG = "RaffleDetails Log";

        public static int CURRENT_RAFFLE;
        public static String RAFFLE_PRICE;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("Home"");
        getMenuInflater().inflate(R.menu.menu_raffle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        Database databaseConnection = new Database(this);
        final SQLiteDatabase dbR = databaseConnection.open();
        final SQLiteDatabase dbT = databaseConnection.open();

        final ArrayList<Raffle> raffles = RaffleTable.selectAll(dbR);
        // final ArrayList<Ticket> tickets = TicketTable.selectAll(dbT);
        final ArrayList<Ticket> tickets = TicketTable.selectTicketsFromRaffle(dbT, MainActivity.SELECTED_RAFFLE_ID);
        long noTickets = tickets.size();

        // this was cancer
        switch (item.getItemId())
        {
            case R.id.view_tickets:
                Intent t = new Intent(this, TicketList.class);
                t.putExtra(String.valueOf(CURRENT_RAFFLE), MainActivity.RAFFLE_ID);
                startActivity(t);
                return true;
            case R.id.delete_raffle:
                if (noTickets >= 1)
                {
                    AlertDialog.Builder builderDelete = new AlertDialog.Builder(RaffleDetails.this);
                    builderDelete.setMessage("This Raffle has already been started").setTitle("Can Not Delete Raffle");
                    builderDelete.setCancelable(true);
                    builderDelete.setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialogDelete = builderDelete.create(); dialogDelete.show();
                }
                else {
                    AlertDialog.Builder builderDelete = new AlertDialog.Builder(RaffleDetails.this);
                    builderDelete.setMessage("This cannot be undone.").setTitle("Delete Raffle?");
                    builderDelete.setCancelable(true);
                    builderDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d(TAG, "Deleting: " + raffles.get(MainActivity.RAFFLE_ID).getName());
                            RaffleTable.removeRaffle(dbR, raffles.get(MainActivity.RAFFLE_ID).getRaffleID(), "");
                            Intent i = new Intent(RaffleDetails.this, MainActivity.class);
                            startActivity(i);

                        }
                    });
                    builderDelete.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialogDelete = builderDelete.create();
                    dialogDelete.show();
                }
                    return true;


            case R.id.add_image:
                /*Intent i = new Intent(this, ImageSelect.class);
                i.putExtra(String.valueOf(CURRENT_RAFFLE), MainActivity.RAFFLE_ID);
                startActivity(i);*/

                AlertDialog.Builder builderImage = new AlertDialog.Builder(RaffleDetails.this);
                builderImage.setMessage("Add an banner image for this raffle?").setTitle("New Banner");
                builderImage.setCancelable(true);
                builderImage.setPositiveButton("Gallery", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        imageView = (ImageView)findViewById(R.id.imgRaffle);
                        openGallery();

                    }
                });
                builderImage.setNegativeButton("Camera", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        // way too much effort, you'd think taking a photo would be a basic function
                        //dispatchTakePictureIntent();
                    }
                });
                AlertDialog dialogImage = builderImage.create(); dialogImage.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    ImageView imageView;
    Button button;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    protected void onCameraActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.raffle_details);
        Database databaseConnection = new Database(this);
        final SQLiteDatabase dbR = databaseConnection.open();
        final SQLiteDatabase dbT = databaseConnection.open();

        final ArrayList<Raffle> raffles = RaffleTable.selectAll(dbR);
        final ArrayList<Ticket> tickets = TicketTable.selectTicketsFromRaffle(dbT, MainActivity.SELECTED_RAFFLE_ID);

        getSupportActionBar().setTitle(raffles.get(MainActivity.RAFFLE_ID).getName());

        /*TextView lblRaffleName = findViewById(R.id.lblRaffleName);
        // this took me way too long to do
        Log.d(TAG, "RAFFLE_ID: " + MainActivity.RAFFLE_ID);
        Log.d(TAG, "NAME: " + raffles.get(MainActivity.RAFFLE_ID).getName());
        lblRaffleName.setText(raffles.get(MainActivity.RAFFLE_ID).getName());*/

        TextView lblRaffleDescription = findViewById(R.id.lblDescription);
        Log.d(TAG, "DESCRIPTION: " + raffles.get(MainActivity.RAFFLE_ID).getDescription());
        lblRaffleDescription.setText("Raffle description:\n" + raffles.get(MainActivity.RAFFLE_ID).getDescription());

        TextView lblPrice = findViewById(R.id.lblPrice);
        lblPrice.setText("Ticket Price: " + "$ " + raffles.get(MainActivity.RAFFLE_ID).getPrice());

        TextView lblTickets = findViewById(R.id.lblTickets);
        lblTickets.setText(tickets.size()+ " Tickets Sold");

        TextView lblRaffleStatus = findViewById(R.id.lblRaffleStatus);
        Log.d(TAG, "STATUS: " + raffles.get(MainActivity.RAFFLE_ID).getStatus());
        if (raffles.get(MainActivity.RAFFLE_ID).getStatus() == 1)
            { lblRaffleStatus.setText("Status:\nOpen"); }
        else
            { lblRaffleStatus.setText("Status:\nClosed"); }




        Button btnUpdate = findViewById(R.id.btnUpdateDetails);
        btnUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CURRENT_RAFFLE = MainActivity.RAFFLE_ID;
                Intent i = new Intent(RaffleDetails.this, RaffleUpdate.class);
                i.putExtra(String.valueOf(CURRENT_RAFFLE), MainActivity.RAFFLE_ID);
                startActivity(i);
            }
        });

        Button btnDraw = findViewById(R.id.btnDraw);
        btnDraw.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent d = new Intent(RaffleDetails.this, RaffleDraw.class);
                d.putExtra(String.valueOf(CURRENT_RAFFLE), MainActivity.RAFFLE_ID);
                startActivity(d);
            }
        });

        Button btnNewTicket = findViewById(R.id.btnNewTicket);
        btnNewTicket.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CURRENT_RAFFLE = MainActivity.SELECTED_RAFFLE_ID;
                RAFFLE_PRICE = raffles.get(MainActivity.RAFFLE_ID).getPrice();
                Intent i = new Intent(RaffleDetails.this, NewTicket.class);
                i.putExtra(String.valueOf(CURRENT_RAFFLE), MainActivity.RAFFLE_ID);
                i.putExtra(String.valueOf(RAFFLE_PRICE), MainActivity.RAFFLE_ID);
                startActivity(i);
            }
        });
    }
}
