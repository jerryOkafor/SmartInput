package me.jerryhanks.smartinput.fieldvalidator;

import android.support.design.widget.TextInputLayout;

import me.jerryhanks.smartinput.R;


/**
 * Created by Potencio on 3/28/2017. @ 1:17 AM
 * For SmartInput
 */

public class PasswordFieldValidator extends BaseValidator {
    private int mMinLength;

    public PasswordFieldValidator(TextInputLayout errorContainer, int length) {
        super(errorContainer);
        mMinLength = length;
        mErrorMessage = mErrorContainer.getResources().getQuantityString(R.plurals.error_week_password, mMinLength, mMinLength);
    }

    @Override
    protected boolean isValid(CharSequence charSequence) {
        return charSequence.length() >= mMinLength;
    }
}
