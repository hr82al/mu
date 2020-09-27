package ru.haval.data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javafx.util.StringConverter;

	@SuppressWarnings("rawtypes")
	public class FxDatePickerConverter extends StringConverter
	{
	    // форматирование даты по умолчанию
	    private String pattern = "yyyy-MM-dd";
	    // Конвертер
	    private DateTimeFormatter dtFormatter, tFormatter;

	    public FxDatePickerConverter()
	    {
	        dtFormatter = DateTimeFormatter.ofPattern(pattern);
	    }

        public FxDatePickerConverter(String pattern)
	    {
	        this.pattern = pattern;
	        tFormatter = DateTimeFormatter.ofPattern(pattern).withLocale(Locale.US);
	    }
   
	    // Корвертируем String в LocalDate
	    public LocalDate fromString(String text)
	    {
	        LocalDate date = null;
	         
	        if (text != null && !text.trim().isEmpty())
	        {
	            date = LocalDate.parse(text, dtFormatter);
	        }
     
	        return date;

	    }
	    public LocalTime fromStringt(String text)
	    {
	        LocalTime time = null;
	         
	        if (text != null && !text.trim().isEmpty())
	        {
	            time = LocalTime.parse(text, tFormatter);
	        }
     
	        return time;

	    }
	     
	    // Конвертируем LocalDate в String
	    public String toString(LocalDate date)
	    {
	        String text = null;
	         
	        if (date != null)
	        {
	            text = dtFormatter.format(date);
	        }
	     
	        return text;
	    }
	    
	    public String toStringt(LocalTime time)
	    {
	        String text = null;
	         
	        if (time != null)
	        {
	            text = tFormatter.format(time);
	        }
	     
	        return text;
	    }

		@Override
		public String toString(Object object) {
			return null;
		}  
	}
