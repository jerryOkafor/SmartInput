package me.jerryhanks.smartinput.fieldvalidator;

import android.support.design.widget.TextInputLayout;
import android.util.Patterns;

/**
 * Created by Potencio on 3/28/2017. @ 1:06 AM
 * For SmartInput
 */

public class EmailFieldValidator extends BaseValidator {
    public EmailFieldValidator(TextInputLayout errorContainer) {
        super(errorContainer);
        mErrorMessage = "Invalid Email Address";
        mEmptyMessage = "Missing Email Address";
    }

    @Override
    protected boolean isValid(CharSequence charSequence) {
        return Patterns.EMAIL_ADDRESS.matcher(charSequence).matches();
    }
}
