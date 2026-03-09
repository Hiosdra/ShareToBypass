# GitHub Actions - Release do Google Play

Ten workflow automatycznie buduje i publikuje aplikację na Google Play Console po utworzeniu GitHub Release.

## ✨ Jak to działa

Workflow uruchamia się automatycznie gdy tworzysz nowy Release w GitHub. Pobiera:
- **Wersję aplikacji** z tagu release (np. `v1.2.3` → versionName: `1.2.3`)
- **Opis zmian (What's New)** z treści release notes
- **Track wydania** na podstawie tagu i typu release (prerelease/normal)

## Wymagane GitHub Secrets

Aby workflow działał poprawnie, musisz skonfigurować następujące sekrety w ustawieniach repozytorium (Settings → Secrets and variables → Actions):

### 1. `KEYSTORE_BASE64`
Keystore zakodowany w base64. Aby go uzyskać:
```bash
base64 -i your-release-keystore.jks | pbcopy
```
Wklej wynik do GitHub Secret.

### 2. `KEYSTORE_PASSWORD`
Hasło do keystore.

### 3. `KEY_ALIAS`
Alias klucza w keystore.

### 4. `KEY_PASSWORD`
Hasło do klucza.

### 5. `GOOGLE_PLAY_SERVICE_ACCOUNT_JSON`
JSON z Google Play Console Service Account. Aby go uzyskać:

1. Przejdź do [Google Play Console](https://play.google.com/console)
2. Wybierz swoją aplikację
3. Przejdź do **Setup → API access**
4. Utwórz nowy Service Account lub użyj istniejącego
5. Nadaj mu uprawnienia "Release Manager" lub "Admin"
6. Pobierz klucz JSON
7. Skopiuj całą zawartość pliku JSON i wklej jako GitHub Secret

## Jak używać

### Tworzenie Release (Zalecane)

1. **Przejdź do repozytorium na GitHub**

2. **Kliknij "Releases" → "Create a new release"**

3. **Ustaw tag:**
   - Format: `v1.0.0`, `v1.0.0-alpha`, `v1.0.0-beta`, `v1.0.0-internal`
   - Tag określa wersję aplikacji i track wydania

4. **Napisz Release notes:**
   
   Simple format (English):
   ```
   - New feature 1
   - Bug fix 2
   - Improved performance
   ```

5. **Wybierz typ release:**
   - ✅ **Normal release** → automatycznie idzie do `production` (lub track z tagu)
   - ☑️ **Pre-release** → automatycznie idzie do `beta` (lub track z tagu)

6. **Kliknij "Publish release"** - Workflow uruchomi się automatycznie!

### Automatyczny wybór track:

| Tag | Pre-release | Track |
|-----|-------------|-------|
| `v1.0.0` | ❌ | production |
| `v1.0.0` | ✅ | beta |
| `v1.0.0-alpha` | — | alpha |
| `v1.0.0-beta` | — | beta |
| `v1.0.0-internal` | — | internal |

### Manualne uruchomienie (opcjonalne)

1. Przejdź do zakładki **Actions** w repozytorium
2. Wybierz workflow "Release to Google Play"
3. Kliknij **Run workflow**
4. Podaj tag release i wybierz track
5. Kliknij **Run workflow**

## What's New Notes

**Release notes are automatically extracted from GitHub Release!**

Simply write your release notes in English:

```markdown
- New feature 1
- Bug fix 2
- Performance improvements
```

The workflow will automatically:
- Extract the content
- Truncate to 500 characters if needed
- Create `whatsnew-en-US` file for Google Play

**Maximum length:** 500 characters (automatically truncated)

## Wersjonowanie

- **`versionCode`** - automatycznie ustawiany na numer uruchomienia GitHub Actions
- **`versionName`** - automatycznie pobierany z tagu release (np. `v1.2.3` → `1.2.3`)

Nie musisz ręcznie edytować `build.gradle.kts`! Workflow zrobi to za Ciebie.

## Troubleshooting

### Błąd "Package not found"
Upewnij się, że aplikacja została już raz ręcznie załadowana do Google Play Console.

### Błąd z podpisywaniem
Sprawdź czy wszystkie sekrety (KEYSTORE_*, KEY_*) są poprawnie ustawione.

### Błąd z Service Account
Upewnij się, że Service Account ma odpowiednie uprawnienia w Google Play Console.

## 🚀 Przykład: Pierwszy Release

### Krok po kroku:

1. **Commituj i wypychaj zmiany:**
   ```bash
   git add .
   git commit -m "Przygotowanie do wersji 1.0.0"
   git push origin main
   ```

2. **Przejdź do GitHub → Releases → Create a new release**

3. **Wypełnij formularz:**
   - **Choose a tag:** `v1.0.0` (utwórz nowy tag)
   - **Release title:** `Version 1.0.0` (opcjonalne)
   - **Describe this release:**
     ```markdown
     - First version of the app
     - Basic ShareToBypass functionality
     - Content sharing support
     ```
   - **Pre-release:** ❌ (zostaw niezaznaczone dla production)

4. **Kliknij "Publish release"**

5. **Sprawdź status:**
   - Przejdź do zakładki **Actions**
   - Poczekaj aż workflow się zakończy (ok. 5-10 minut)
   - Sprawdź Google Play Console czy aplikacja się pojawiła

### Następne wersje:

Dla wersji beta/alpha:
```bash
# Tag: v1.1.0-beta
# Pre-release: ✅ lub użyj tagu z -beta/-alpha
```

Dla wersji production:
```bash
# Tag: v1.1.0
# Pre-release: ❌
```

## 📖 Dodatkowe zasoby

- [GitHub Releases Documentation](https://docs.github.com/en/repositories/releasing-projects-on-github/about-releases)
- [Google Play Console Help](https://support.google.com/googleplay/android-developer/)
