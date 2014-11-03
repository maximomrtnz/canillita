package canillita.maximomrtnz.com.canillita.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import canillita.maximomrtnz.com.canillita.R;

/**
 * Created by maxi on 11/3/14.
 */
public class InputDialog extends DialogFragment {

    // Use this instance of the interface to deliver action events
    private InputDialogListener mListener;

    /* The activity that creates an instance of this dialog fragment must
    * implement this interface in order to receive event callbacks.
    * Each method passes the DialogFragment in case the host needs to query it. */
    public interface InputDialogListener {
        public void onDialogPositiveClick(String input);
        public void onDialogNegativeClick();
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (InputDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement InputDialogListener");
        }
    }


    public static InputDialog newInstance(int title, int message){

        // Instance a new InputDialog
        InputDialog i = new InputDialog();

        // Supply title input and message input as an arguments
        Bundle args = new Bundle();
        args.putInt("title", title);
        args.putInt("message", message);

        // Set the arguments to de dialog
        i.setArguments(args);

        // Return de dialog
        return i;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Get arguments from the bundle
        int title = getArguments().getInt("title");
        int message = getArguments().getInt("message");

        // Create the Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Add the title
        builder.setTitle(title);

        // Add the message
        builder.setMessage(message);

        // Set up the input
        final EditText input = new EditText(getActivity());

        // Specify the type of input expected
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Add the buttons
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                mListener.onDialogPositiveClick(input.getText().toString());
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                mListener.onDialogNegativeClick();
            }
        });
        // Set other dialog properties


        // Create the AlertDialog
        return builder.create();

    }
}
