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

- **Launcher-App** mit MainActivity (erscheint im App-Menü)
- **Startbildschirm-Widgets** mit echten Kursdaten (Twelve Data API) via Jetpack Glance / AppWidgetProvider
- **Widget-Editor** zur Konfiguration von Symbol, Größe, Layout und Anzeigeoptionen
- **Pill Bottom Navigation** im modernen Glass-Design
- **Theme-Einstellung**: Hell / Dunkel / System-abhängig
- **Material 3 UI**, deutsche Lokalisierung
- **API-Schlüssel verschlüsselt gespeichert** (Android EncryptedSharedPreferences, AES256-GCM)
- **Lokaler Cache** (Room) für Instrumente, Widget-Konfigurationen und Kurs-Snapshots
- **Automatische Kurs-Aktualisierung** per WorkManager
- **Dependency Injection** via Hilt
- **Platzhalter-Logo** im Glass-Design
- **Cleanes, modernes Design** mit abgerundeten Karten, Sanft-Verläufen und sanften Schatten

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

## Installation (Wichtig!)

Da eine Debug-APK keine feste Signatur hat, muss sie **immer zuerst deinstalliert** werden, bevor eine neuere Version installiert werden kann:

1. Alte StockGlass-Version deinstallieren
2. Neue `app-debug.apk` aus den GitHub Actions Artifacts herunterladen und installieren

## Bekannte Einschränkungen

- **Kein lokaler Build möglich** (kein Android SDK in dieser Umgebung). Verifizierung erfolgt ausschließlich in GitHub Actions.
- **API-Key-Sicherheit**: Der Schlüssel liegt auf dem Gerät verschlüsselt vor. Bei Diebstahl des entsperrten Geräts oder Kompromittierung des Android-Keystores ist er lesbar. Es gibt keinen 100%igen Schutz für clientseitig gespeicherte API-Schlüssel.
- **Twelve Data Free-Plan**: Begrenzte API-Calls pro Tag/Monat. Keine Echtzeit- oder Streaming-Daten; Kurse können verzögert sein.
- **Keine Anlageberatung**: Die angezeigten Kurse dienen nur Informationszwecken.
- **Debug-APK Updates**: Immer zuerst deinstallieren, dann neu installieren (Android-Sicherheitsmechanismus).

## Lizenz

Privates Projekt. Keine öffentliche Lizenz.
