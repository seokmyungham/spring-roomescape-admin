package roomescape.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import roomescape.domain.Reservation;
import roomescape.domain.ReservationRepository;
import roomescape.service.dto.ReservationRequestDto;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @DisplayName("모든 예약 정보 조회 및 의존 객체 상호작용 테스트")
    @Test
    void find_all_reservations_test() {
        Reservation reservation1 = new Reservation(1L, "안돌", "2024-09-08", 1L, "00:00");
        Reservation reservation2 = new Reservation(2L, "재즈", "2024-11-30", 1L, "00:00");
        List<Reservation> reservations = List.of(reservation1, reservation2);
        given(reservationRepository.findAllReservations()).willReturn(reservations);

        reservationService.findAllReservations();
        verify(reservationRepository, times(1)).findAllReservations();
    }

    @DisplayName("예약 저장 및 의존 객체 상호작용 테스트")
    @Test
    void create_reservation_test() {
        ReservationRequestDto requestDto = new ReservationRequestDto("재즈", "2024-04-21", 1L);
        Reservation reservation = new Reservation(1L, "재즈", "2024-04-21", 1L, "15:30");
        given(reservationRepository.insertReservation(requestDto.toReservation())).willReturn(reservation);

        reservationService.createReservation(requestDto);
        verify(reservationRepository, times(1)).insertReservation(requestDto.toReservation());
    }
}
