// IWeatherDataInterface.aidl
package aidl;

// Declare any non-default types here with import statements

interface IWeatherDataInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    String getCurrentTemperature();
    String getCurrentHumidity();
    //life
    String getCloth();
    String getSick();
    String getAirConditioner();
    String getWashingCar();
    String getSports();
    String getUltraviolet();
    //polution
    String getPM25();

    String getLunar();//获取农历日期

    List<String> getAllChineseCities();

    void refreshData();
}
