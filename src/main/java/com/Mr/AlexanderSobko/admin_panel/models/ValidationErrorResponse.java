package com.Mr.AlexanderSobko.admin_panel.models;

import java.util.List;

public record ValidationErrorResponse(List<Violation> violations) {

}
