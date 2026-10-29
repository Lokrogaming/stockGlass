# StockGlass

Native Android App für Aktien-Widgets auf dem Startbildschirm. Datenquelle: [Twelve Data](https://twelvedata.com).

## Paket-ID

`dev.lokro.stockglass`

## Voraussetzungen

- Android SDK (API 29–35)
- Java 17+
- Gradle 8.x

Da keine Android-SDK lokal verfügbar ist, wird der Build exklusiv über GitHub Actions ausgeführt.

## Features

- Startbildschirm-Widgets mit echten Kursdaten (Twelve Data API)
- API-Schlüssel verschlüsselt gespeichert (Android EncryptedSharedPreferences, AES256-GCM)
- Lokale Cache-DB (Room) für Instrumente, Widget-Konfigurationen und Kurs-Snapshots
- Automatische Kurs-Aktualisierung per WorkManager
- Material 3 UI (Hell/Dark), deutsche Lokalisierung
- Hilt Dependency Injection, Kotlin Coroutines, Kotlin Serialization

## Aufbau

```
app/src/main/java/dev/lokro/stockglass/
├── di/                 # Hilt Module + Application
├── domain/
│   ├── model/          # Domain-Modelle (Quote, Instrument, WidgetConfig, …)
│   ├── mapper/         # Entity <-> Domain Mapper
│   └── usecase/        # Use Cases + Result sealed class
├── data/
│   ├── remote/         # Retrofit API, DTOs, API-Result-Mapping
│   ├── repository/     # Repository-Implementierungen
│   ├── security/       # EncryptedStore (API-Key)
│   ├── cache/          # Request-Deduplizierung, Exponential-Backoff
│   └── local/          # Room Entities, DAOs, Database, TypeConverters
├── ui/
│   ├── navigation/     # NavHost + Routen
│   ├── screens/        # Compose Screens (Home, Search, Settings, API-Key, Editor, Widget-Liste)
│   └── theme/          # Material 3 Theme
├── widget/             # Jetpack Glance AppWidget + Receiver
├── work/               # RefreshWorker (WorkManager)
└── util/               # NumberFormatter, GradientUtils
```

## API-Schlüssel einrichten

1. Kostenlosen API-Schlüssel unter [twelvedata.com](https://twelvedata.com) anfordern.
2. App öffnen → Einstellungen → API-Schlüssel verwalten.
3. Schlüssel eingeben und speichern.

Der Schlüssel wird **nur lokal** auf dem Gerät verschlüsselt abgelegt und verlässt das Gerät nicht (außer an die Twelve Data API). Er wird **nicht** im Quellcode oder im Repository hinterlegt.

## Build (GitHub Actions)

Der Build läuft über `.github/workflows/android.yml`:

- `gradle assembleDebug` → Debug-APK
- `gradle test` → Unit-Tests (Robolectric/JUnit)

Artifacts: `app-debug.apk`

## Bekannte Einschränkungen

- **Kein lokaler Build möglich** (kein Android SDK in dieser Umgebung). Verifizierung erfolgt ausschließlich in GitHub Actions.
- **API-Key-Sicherheit**: Der Schlüssel liegt auf dem Gerät verschlüsselt vor. Bei Diebstahl des entsperrten Geräts oder Kompromittierung des Android-Keystores ist er lesbar. Es gibt keinen 100%igen Schutz für clientseitig gespeicherte API-Schlüssel.
- **Twelve Data Free-Plan**: Begrenzte API-Calls pro Tag/Monat. Keine Echtzeit- oder Streaming-Daten; Kurse können verzögert sein.
- **Keine Anlageberatung**: Die angezeigten Kurse dienen nur Informationszwecken.

## Lizenz

Privates Projekt. Keine öffentliche Lizenz.
