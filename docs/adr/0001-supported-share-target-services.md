# 1. Selection of supported share-target services

## Status

Accepted

## Context

The app works by registering one Android `Activity` per paywall-bypass /
article-summarizer service (see `BaseShareActivity` and its subclasses). Each
activity builds a target URL from the shared link and opens it in a Chrome
Custom Tab. Adding a service only makes sense if the destination site accepts
the article URL as a query parameter or path segment and opens the article (or
an archived copy of it) directly — if it doesn't, the user just lands on the
service's homepage and has to paste the link manually anyway, which defeats
the purpose of "share to bypass."

Several candidate services were researched and, where possible, manually
tested to confirm the assumption above before being wired into the app.

## Decision

### Supported (implemented as share targets)

| Service                | URL format                                              | Verification                                                                                                                                                                         |
| ---------------------- | ------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| Smry.ai                | `https://smry.ai/<url>`                                 | Pre-existing, in production                                                                                                                                                          |
| RemovePaywall (search) | `https://www.removepaywall.com/search?url=<url>`        | Pre-existing, in production                                                                                                                                                          |
| RemovePaywalls.com     | `https://removepaywalls.com/<url>`                      | Fixed 2026-07-01 — was incorrectly pointing at `removepaywall.com` (same domain as the "search" variant above) despite its label promising the distinct `removepaywalls.com` service |
| PaywallBuster          | `https://paywallbuster.com/articles?article=<url>`      | Pre-existing, in production                                                                                                                                                          |
| Archive.ph             | `https://archive.ph/newest/<url>`                       | Confirmed working                                                                                                                                                                    |
| Archive Buttons        | `https://www.archivebuttons.com/articles?article=<url>` | Confirmed working via manual test (2026-07-01); initial guess of `/?url=<url>` was wrong                                                                                             |
| Wayback Machine        | `https://web.archive.org/web/2/<url>`                   | Confirmed working via manual test (2026-07-01)                                                                                                                                       |
| Bypass Paywall Reader  | `https://www.bypasspaywallreader.com/?url=<url>`        | Confirmed working via manual test (2026-07-01)                                                                                                                                       |

### Analyzed and rejected (not implemented)

| Service                               | Reason rejected                                                                                                                                                                                              |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **12ft.io**                           | Confirmed permanently shut down in July 2025 after the News/Media Alliance pressured its host (Vercel) into taking it offline over copyright-circumvention claims. Adding it would ship a dead share target. |
| **PaywallReader** (paywallreader.com) | Initially implemented with a guessed URL (`/search?url=`), but manual testing (2026-07-01) showed the site no longer resolves at all. Removed rather than kept as a dead link.                               |
| **1ft.io**                            | Announced shutdown; domain still resolves but reliability is unconfirmed/degraded. Too uncertain to add without direct testing.                                                                              |
| **Google Cache**                      | Discontinued by Google; no longer usable as a bypass method.                                                                                                                                                 |
| **Unpaywall**                         | Solves a different problem (legal open-access copies of scholarly papers) and is primarily a browser extension, not a URL-based web tool — doesn't fit this app's share-intent architecture.                 |
| **Zette**                             | Paid, publisher-compliant unlocking service, not a URL-passthrough site — doesn't fit this app's share-intent architecture.                                                                                  |

## Consequences

- All eight supported share targets have been manually confirmed to open the
  shared article directly rather than just the service's homepage.
- If a currently-supported service goes offline or changes its URL scheme in
  the future, follow the same pattern used for PaywallReader: verify first,
  then remove the activity, its manifest entry, its string resource, and its
  unit tests rather than leaving a dead share target in the app.
