package com.techcareer.role;

// final verirsem;
// 1-) Değişkende: sabit değer
// 2-) Metotda: Override edilemez
// 3-) Classta: Kalıtımlanamaz, edilemez

// 1-) Değişkende final verirsek;
// 1-a_) Değeri sabit yapar.
// 2-a_) Sadece Get metodunu eklemeye yarar
// 3-a_) Bizi Constructur yazmamıza zorlar

public enum Status {
    INCOMPLETE(1L,"incomplete"),
    COMPLETE(2L,"complete");

    // variable
    private final Long key;
    private final String value;

    // Constructor (Parametreli constructor)
    // Constructor'a private verirsek;
    // Bu Enum'ın instance(new) oluşturulmasına izin vermiyor.
    private Status(Long key, String value) {
        this.key = key;
        this.value = value;
    }//end Status Constructor

    // GETTER
    public Long getKey() {
        return key;
    }//end getKey

    //STRING
    public String getValue() {
        return value;
    }//end getValue
}//end Enum Status
