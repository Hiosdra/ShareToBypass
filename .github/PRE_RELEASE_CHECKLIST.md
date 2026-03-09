# ✅ Pre-Release Checklist

Przed utworzeniem pierwszego release, upewnij się że:

## 1. GitHub Secrets skonfigurowane
- [ ] `KEYSTORE_BASE64` - keystore zakodowany w base64
- [ ] `KEYSTORE_PASSWORD` - hasło do keystore
- [ ] `KEY_ALIAS` - alias klucza
- [ ] `KEY_PASSWORD` - hasło do klucza
- [ ] `GOOGLE_PLAY_SERVICE_ACCOUNT_JSON` - JSON z Service Account

## 2. Google Play Console
- [ ] Aplikacja została już raz ręcznie załadowana (aby utworzyć listing)
- [ ] Service Account ma uprawnienia "Release Manager" lub "Admin"
- [ ] API access jest włączony dla aplikacji

## 3. Keystore
- [ ] Masz backup keystore w bezpiecznym miejscu
- [ ] Znasz wszystkie hasła
- [ ] Keystore jest zakodowany w base64 i dodany do GitHub Secrets

## 4. Kod aplikacji
- [ ] `applicationId` w `app/build.gradle.kts` zgadza się z package name w Google Play
- [ ] Wszystkie testy przechodzą: `./gradlew check`
- [ ] Aplikacja buduje się lokalnie: `./gradlew bundleRelease`

## 5. Dokumentacja
- [ ] Przeczytano [RELEASE_SETUP.md](.github/RELEASE_SETUP.md)
- [ ] Przeczytano [QUICK_START.md](.github/QUICK_START.md)
- [ ] Przygotowano Release notes (możesz użyć [szablonu](.github/RELEASE_NOTES_TEMPLATE.md))

---

## Gotowy? 🚀

1. Idź do [Releases](../../releases)
2. Kliknij "Create a new release"
3. Użyj tagu `v1.0.0`
4. Dodaj Release notes
5. Publish!

Workflow automatycznie zbuduje i wyśle aplikację do Google Play! 🎉

