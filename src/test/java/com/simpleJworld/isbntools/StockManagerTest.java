package com.simpleJworld.isbntools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StockManagerTest {

    ExternalISBNDataService testDatabaseService;
    ExternalISBNDataService testWebService;
    StockManager stockManager;

    @BeforeEach
    public void setup() {
        testDatabaseService = mock(ExternalISBNDataService.class);
        testWebService = mock(ExternalISBNDataService.class);
        stockManager = new StockManager();
        stockManager.setDatabaseService(testDatabaseService);
        stockManager.setWebService(testWebService);

    }

    @Test
    public void shouldReturnACorrectLocatorCode() {
        String isbn = "0553213119";
        when(testDatabaseService.lookup(isbn)).thenReturn(null);
        when(testWebService.lookup(isbn)).thenReturn(new Book(isbn, "Moby Dick", "Herman Melville "));

        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("3119H2", locatorCode);
    }


    @Test
    public void databaseServiceIsUsedIfDataIsPresentInDatabase() {
        String isbn = "0553213119";
        when(testDatabaseService.lookup(isbn)).thenReturn(new Book(isbn, "Moby Dick", "Herman Melville "));
        when(testWebService.lookup(isbn)).thenReturn(null);

        stockManager.getLocatorCode(isbn);

        verify(testDatabaseService, times(1)).lookup(isbn);
        verify(testWebService, never()).lookup(anyString());
    }

    @Test
    public void webServiceIsUsedIfDataNotPresentInDatabase() {
        String isbn = "0553213119";
        given(testDatabaseService.lookup(isbn)).willReturn(null);
        given(testWebService.lookup(isbn)).willReturn(new Book(isbn, "Moby Dick", "Herman Melville "));

        stockManager.getLocatorCode(isbn);

        verify(testDatabaseService, times(1)).lookup(isbn);
        verify(testWebService, times(1)).lookup(isbn);
    }
}
