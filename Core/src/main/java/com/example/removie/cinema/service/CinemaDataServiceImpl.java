package com.example.removie.cinema.service;

import com.example.removie.cinema.CinemaData;
import com.example.removie.cinema.exception.CinemaParsingFailException;
import com.example.removie.kobis.cinema.KOBISCinemaElement;
import com.example.removie.kobis.cinema.KOBISCinemaDataParser;
import com.example.removie.kobis.exception.DocIOException;
import com.example.removie.kobis.exception.DocResponseNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 상영관 리스트를 생성하는 클래스입니다.
 *
 * @author An_Jicheol
 * @version 1.0
 */

@Service
public class CinemaDataServiceImpl implements CinemaDataService{
    private final KOBISCinemaDataParser KOBISCinemaDataParser;
    private final KOBISCinemaElement kobisCinemaElement;

    @Autowired
    public CinemaDataServiceImpl(KOBISCinemaDataParser KOBISCinemaDataParser, KOBISCinemaElement kobisCinemaElement) {
        this.KOBISCinemaDataParser = KOBISCinemaDataParser;
        this.kobisCinemaElement = kobisCinemaElement;
    }

    /**
     * 매개변수로 받은 영화에 현재 상영관 정보를 리턴합니다.
     *
     * <p>
     * {@code kobisCinemaElement.getCinemaCodeElements(movieCode)} 와 {@code cinemaDataParser::extractCinemaData}
     * 는 각각 {@link DocIOException},{@link DocResponseNullException} 와 {@link CinemaParsingFailException} 등의
     * 예외가 발생할 수 있습니다. 단 해당 메서드에서는 빈 리스트를 반환합니다.
     * </p>
     *
     *
     * @param movieCode 상영 중인 영화에 대한 식별 코드입니다.
     * @return 상영관 데이터 리시트 (빈 리스트 일 수 있음)
     */

    @Override
    public List<CinemaData> getCinemaData(String movieCode) {
        return kobisCinemaElement.getCinemaCodeElements(movieCode).stream()
                .map(KOBISCinemaDataParser::extractCinemaData)
                .filter(cinemaData -> !cinemaData.isINVALID())
                .collect(Collectors.toList());
    }
}
