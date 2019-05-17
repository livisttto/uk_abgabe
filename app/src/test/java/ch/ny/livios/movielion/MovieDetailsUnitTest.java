package ch.ny.livios.movielion;

import org.junit.Test;


import java.util.ArrayList;

import static ch.ny.livios.movielion.ui.moviedetails.MovieDetailsFragment.getGenresString;
import static ch.ny.livios.movielion.ui.moviedetails.MovieDetailsFragment.longToUSD;
import static ch.ny.livios.movielion.ui.moviedetails.MovieDetailsFragment.minToString;
import static org.junit.Assert.assertEquals;

public class MovieDetailsUnitTest {
    @Test
    public void hoursMinsCalculator_OnlyHours(){
        assertEquals(minToString(600), "10h");
    }

    @Test
    public void hoursMinsCalculator_OnlyMinutes(){
        assertEquals(minToString(43), "43min");
    }

    @Test
    public void hoursMinsCalculator_HoursAndMinutes(){
        assertEquals(minToString(155), "2h 35min");
    }

    @Test
    public void LongToUSD_ShortNumber(){
        assertEquals(longToUSD(155l), "$155.00");
    }

    @Test
    public void LongToUSD_LongNumber(){
        assertEquals(longToUSD(87648757645843l), "$87,648,757,645,843.00");
    }
}
