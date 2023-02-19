## Kideappdataanalyysivehje

[status](http://10.114.32.28:8080/job/kideappdataanalyysivehje/badge/icon)

### Käynnistäminen

view.StartProject::main

### Env filu

Luo suoraan `src` kansioon `.env` tiedosto
Sinne voi laittaa muuttujia
`JOKU_AVAIN=joku hieno arvo`
Sit niitä voi lukea sovelluksessa config luokan avulla

```java
Config.get("JOKU_AVAIN")
```

Voi kans asettaa default arvon

```java
Config.get("JOKU_AVAIN", "oon default arvo");
```
