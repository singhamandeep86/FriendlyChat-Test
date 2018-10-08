# FriendlyChat-Test

This repository contains code for the FriendlyChat-Test project based on the project placed in the [Firebase in a Weekend: Android by Google](https://www.udacity.com/course/firebase-in-a-weekend-by-google-android--ud0352) Udacity course.

This version of the application adds additional app tracking via Firebase, Appsflyer, and Google Analytics SDK

This version also stiches sessions between AMP pages and app views on Google Analytics properties.

## Overview

FriendlyChat-Test is an app that allows users to send and receive text in realtime across platforms. It is also designed to receive Android App Links from www.codellect.org.

A user session can start on this website via a visit to a normal web-page or an AMP page. While clicking on a link to the AMP page from Google Search would always open the page in the [Google AMP viewer](https://support.google.com/websearch/answer/7220196), any further clicks on a codellect.org link will lead user to FriendlyChat-Test app. Clicking on a non-AMP codellect.org link from Google Search would also lead the user directly to FriendlyChat-Test app.

While sessions (and other interactions) on the website (www.codellect.org) are reported to the GA property UA-101537658-1, the sessions (and other interactions) on FriendlyChat-Test are reported to GA property UA-126353338-4.

However, FriendlyChat-Test is also designed to stitch sessions between traffic that originates on an AMP page and lands inside the app. Note that FriendlyChat-Test does not stitch sessions between traffic that originates on non-AMP mobile pages and lands inside the app- since such use cases are limited.

Sessions stitching between App and AMP traffic is achieved by utilizing a combination of [Google AMP Client ID API]( https://support.google.com/analytics/answer/7486764?hl=en) and [AMP Variable Substitution]( https://github.com/ampproject/amphtml/blob/master/spec/amp-var-substitutions.md). Check the next section for details

## Setup

Requires Firebase, Appslfyer, and Google Analytics SDK integration. The project also requires you to own an AMP page and website.


### Passing parameters from AMP to App
[AMP Variable Substitution](https://github.com/ampproject/amphtml/blob/master/spec/amp-var-substitutions.md) can be utilized to pass necessary parameters from AMP to App. Parameters such as **gclid** are required to report conversions and **client id** is required to stitch the GA sessions once user is automatically redirected from AMP to App.

Following code snippet provides implementation on how to pass query parameters. This is a hyperlink on the AMP landing page that leads to the login page of the website. Notice how **client id (cid)** and **gclid** are passed as params to the URL. The hyperlink also appends a static parameter called **amp** set to **true** so that receiving webpage or App screen can log this traffic as AMP generated.

```
<a href="/login" data-amp-replace="CLIENT_ID QUERY_PARAM" data-amp-addparams="cid=CLIENT_ID(AMP_ECID_GOOGLE)&gclid=QUERY_PARAM(gclid)&amp=true">

```
**Client ID** is stored in **AMP_ECID_GOOGLE** cookie. For inorganic clicks, **gclid** is present in the AMP page URL. QUERY_PARAM is utilized to extract from AMP URL and append it to outgoing URL.

It is also recommended to create a session scoped custom dimension in GA to be able to track traffic and conversions led by AMP pages. Google Analytics can be extended for custom dimension by specifying extraUrlParams as follows:

```
<amp-analytics type="googleanalytics">
<script type="application/json">
{
  "vars": {
    "account": "UA-101537658-1"
  },
  "extraUrlParams": {
    "cd1": "true"
  },
  "triggers": {
    "trackPageviewWithCustomData": {
      "on": "visible",
      "request": "pageview"
    }
  }
}
</script>
</amp-analytics>
```

## License
See [LICENSE](LICENSE)
