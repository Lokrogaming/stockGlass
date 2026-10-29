# STATUS — StockGlass CI

## Aktueller Stand

Der Build in GitHub Actions schlägt noch fehl. Der Code ist vollständig geschrieben, die CI-Umgebung wird noch stabilisiert.

## Letzter bekannter Fehler (CI Run #40898197260)

```
ERROR: resource mipmap/ic_launcher (aka dev.lokro.stockglass:mipmap/ic_launcher) not found.
ERROR: resource mipmap/ic_launcher_round (aka dev.lokro.stockglass:mipmap/ic_launcher_round) not found.
```

Die `mipmap-*` Verzeichnisse existierten, waren aber leer. Vector-Drawables als Platzhalter wurden hinzugefügt (Commit `5b6d733` + mipmap-XMLs). Ein neuer CI-Lauf ist ausstehend.

## Bereits behobene CI-Fehler (chronologisch)

| # | Fehler | Fix |
|---|--------|-----|
| 1 | `sdkmanager: command not found` | Manuelle Installation der Android Command Line Tools via `wget` + `unzip` |
| 2 | `ClassNotFoundException: com.android.sdklib.tool.sdkmanager.SdkManagerCli` | `cmdline-tools/latest/` Verzeichnisstruktur korrekt einrichten |
| 3 | `mkdir: Permission denied` auf `/usr/lib/android-sdk` | `$HOME/android-sdk` statt `/usr/lib/android-sdk` verwenden |
| 4 | `Could not find or load main class ... SdkManagerCli` | Inhalt des ZIP direkt in `cmdline-tools/latest/` entpacken und flatten |
| 5 | `$HOME` expandiert nicht in `env:` von GitHub Actions | Pfad hardcoded auf `/home/runner/android-sdk` |
| 6 | `InvalidUserDataException: Invalid TOML definition` | Plugin-Alias-Namen ohne Punkte (`androidApplication`, `kotlinAndroid`, …) |
| 7 | `Unresolved reference 'android'` | Plugins im App-Modul ohne `apply false` anwenden |
| 8 | `Unresolved reference 'kapt'` | `kapt` durch `annotationProcessor` ersetzt |
| 9 | `Could not find com.google.dagger:hilt:2.52` | Hilt auf 2.48.1 downgraded (2.52 nicht auf Maven Central) |
| 10 | `com.google.dagger:hilt` existiert nicht als Artifact | Nur `hilt-android` referenzieren |
| 11 | `minifyEnabled: Unresolved reference` | `minifyEnabled`-Blöcke entfernt |
| 12 | `compose_compiler: Unresolved reference` | Direkte Versionsnummer statt `libs.versions.compose_compiler.get()` |
| 13 | `Theme.Material3.DayNight.NoActionBar` nicht gefunden | Theme auf `Theme.AppCompat.Light.NoActionBar` vereinfacht |
| 14 | `mipmap/ic_launcher` nicht gefunden | Vector-Drawables als Platzhalter in allen `mipmap-*` Dichten |

## Offene Punkte

1. **CI-Lauf ausstehend**: Nach dem Hinzufügen der mipmap-Vector-Drawables muss ein neuer CI-Run abgewartet werden.
2. **Lokale Build-Verifizierung**: Nicht möglich (kein Android SDK in Termux). Der Build kann nur über GitHub Actions verifiziert werden.
3. **API-Key**: Nicht im Code hinterlegt. Muss in der App unter Einstellungen → API-Schlüssel verwalten eingegeben werden.

## Repo

- https://github.com/Lokrogaming/stockGlass
- Branch: `master`
- Letzter Commit: `5b6d733` (Theme vereinfacht) + mipmap-XMLs (uncommitted zum Zeitpunkt dieses Status)
