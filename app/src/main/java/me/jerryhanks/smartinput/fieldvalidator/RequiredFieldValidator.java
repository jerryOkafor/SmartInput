package me.jerryhanks.smartinput.fieldvalidator;

import android.support.design.widget.TextInputLayout;

/**
 * Created by Potencio on 3/28/2017. @ 12:50 AM
 * For SmartInput
 */

public class RequiredFieldValidator extends BaseValidator {
    public RequiredFieldValidator(TextInputLayout errorContainer) {
        super(errorContainer);
        mEmptyMessage = "This Field is required";
    }

    @Override
    protected boolean isValid(CharSequence charSequence) {
        return charSequence != null && charSequence.length() > 0;
    }
}
