/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Settings;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Kevin Cedeño
 * @author Gustavo Castro
 * @author Kevin Valle
 */
public class Writer {
    
    
    
    
public List<LocalDate> getRangeDates(LocalDate initDate, LocalDate endDate) {
    return initDate.datesUntil(endDate.plusDays(1)).collect(Collectors.toList());
}
    
}
