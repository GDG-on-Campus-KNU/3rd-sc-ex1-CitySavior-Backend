package GDSCKNU.CitySavior.service;

import GDSCKNU.CitySavior.dto.ReportRequestDto;

public interface ReportService {

    public Long saveReport(ReportRequestDto requestDto, double weight, String img_url);
}
