package com.techcareer.profiles;

import org.springframework.stereotype.Component;

//Bu sınıf, "IChooseProfile" arayüzünü uygular ve "message" metodu üzerinde farklı profillere göre mesaj döndürür.
// @Component: IChooiseProfile nesnesi Spring nesnesi olması için
@Component
public interface IChooiseProfile {
    public String message(String name);

}//end IChooiseProfile
