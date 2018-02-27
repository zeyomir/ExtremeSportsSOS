package io.github.zeyomir.extremesportssos.domain.usecase;


import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Locale;

import io.github.zeyomir.extremesportssos.domain.driver.SmsDriver;
import io.github.zeyomir.extremesportssos.domain.entity.Coordinates;
import io.github.zeyomir.extremesportssos.domain.entity.SosContact;
import io.github.zeyomir.extremesportssos.domain.repository.LocalRepository;
import io.github.zeyomir.extremesportssos.domain.repository.LocationRepository;
import io.reactivex.Completable;
import io.reactivex.Single;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.hamcrest.core.StringStartsWith.startsWith;


public class SendSosMessageUseCaseTest {
    private SendSosMessageUseCase sendSosMessageUseCase;

    private final String telephoneNumber = "123456789";
    private final String message = "message";
    private final Coordinates coordinates = new Coordinates(50.1, 20.1);

    private final ArgumentCaptor<SosContact> contactCaptor = ArgumentCaptor.forClass(SosContact.class);
    private final ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);

    @Before
    public void setUp() {
        final LocalRepository localRepository = createLocalRepository();
        final LocationRepository locationRepository = createLocationRepository();
        final SmsDriver smsDriver = createSmsDriver();
        sendSosMessageUseCase = new SendSosMessageUseCase(localRepository, locationRepository, smsDriver);
    }

    private LocalRepository createLocalRepository() {
        final LocalRepository mock = Mockito.mock(LocalRepository.class);
        Mockito.when(mock.fetchContact()).thenReturn(new SosContact(telephoneNumber, null));
        Mockito.when(mock.fetchMessage()).thenReturn(message);
        return mock;
    }

    private LocationRepository createLocationRepository() {
        final LocationRepository mock = Mockito.mock(LocationRepository.class);
        Mockito.when(mock.getCurrentCoordinates()).thenReturn(Single.just(coordinates));
        return mock;
    }

    private SmsDriver createSmsDriver() {
        final SmsDriver mock = Mockito.mock(SmsDriver.class);
        Mockito.when(mock.sendSms(contactCaptor.capture(), messageCaptor.capture())).thenReturn(Completable.complete());
        return mock;
    }

    @Test
    public void check_message_properly_composed_and_contactInfo_is_correct() {
        sendSosMessageUseCase.execute()
                .test()
                .assertComplete();

        assertThat("different tel number", telephoneNumber, equalTo(contactCaptor.getValue().getContactInfo()));
        final String sosMessage = messageCaptor.getValue();
        assertThat("different message", sosMessage, startsWith(message));
        assertThat("lacks map link", sosMessage, containsString("google.com/maps"));
        assertThat("different coordinates", sosMessage, endsWith(String.format(Locale.US, "%.1f,%.1f", coordinates.getLatitude(), coordinates.getLongitude())));
    }
}
