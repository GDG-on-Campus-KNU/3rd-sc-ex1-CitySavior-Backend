package GDSCKNU.CitySavior.exception;

import GDSCKNU.CitySavior.exception.error.ReportError;
import GDSCKNU.CitySavior.global.exception.model.ExceptionBase;

public class ReportException extends ExceptionBase {

    public ReportException(ReportError reportError) {
        super(reportError, reportError.getMessage());
    }

}
