# FriendlyChat-Test

This repository contains code for the FriendlyChat-Test project based on the project placed in the [Firebase in a Weekend: Android by Google](https://www.udacity.com/course/firebase-in-a-weekend-by-google-android--ud0352) Udacity course.

This version of the application adds additional app tracking via Firebase, Appsflyer, and Google Analytics SDK

This version also stiches sessions between AMP pages and app views on Google Analytics properties.

## Overview

FriendlyChat-Test is an app that allows users to send and receive text in realtime across platforms. It is also designed to receive Android App Links from www.codellect.org. 

A user session can start on this website via a visit to a normal web-page or an AMP page. While clicking on a link to the AMP page from Google Search would always open the page in the [Google AMP viewer](https://support.google.com/websearch/answer/7220196), any further clicks on a codellect.org link will lead user to FriendlyChat-Test app. Clicking on a non-AMP codellect.org link from Google Search would also lead the user directly to FriendlyChat-Test app.

While sessions (and other interactions) on the website (www.codellect.org) are reported to the GA property UA-101537658-1, the sessions (and other interactions) on FriendlyChat-Test are reported to GA property UA-126353338-4. 

However, FriendlyChat-Test is also designed to stitch sessions between traffic that originates on an AMP page and lands inside the app. Note that FriendlyChat-Test does not stitch sessions between traffic that originates on non-AMP mobile pages and lands inside the app- since such use cases are limited. 

Sessions stitching between App and AMP traffic is achieved by utilizing a combination of [Google AMP Client ID API]( https://support.google.com/analytics/answer/7486764?hl=en) and [AMP Linker]( https://amphtml.wordpress.com/2018/09/17/measuring-user-journeys-across-the-amp-cache-and-your-website/). Check the setup section for details


## Passing parameters from AMP to App
[AMP Variable Substitution](https://github.com/ampproject/amphtml/blob/master/spec/amp-var-substitutions.md) can be utilized to pass necessary parameters from AMP to App. Parameters such as **gclid** are required to report conversions and **client id** is required to stitch the GA sessions once user is automatically redirected from amp to app. Following code snippet provides implementation on how to pass query parameters: 

```
<a href="/login" data-amp-replace="CLIENT_ID QUERY_PARAM" data-amp-addparams="cid=CLIENT_ID(AMP_ECID_GOOGLE)&gclid=QUERY_PARAM(gclid)">

```
Client ID is stored in AMP_ECID_GOOGLE cookie. For inorganic clicks, gclid is present in the AMP page URL. QUERY_PARAM is utilized to extract from AMP URL and append it to outgoing URL. 

It is also recommended to create a custom dimension to be able to filter conversions happening due to AMP. This will include conversions happening in web as well as app. Google Analytics can be extended for custom dimension by specifying extraURLparams as follows:

```
<amp-analytics type="googleanalytics">
<script type="application/json">
{
  "vars": {
    "account": "UA-XXXXX-Y"
  },
  "extraUrlParams": {
    ***"cd3": "AMP"***
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




## Setup

Requires Firebase, Appslfyer, Google Analytics and an AMP property.

## License
See [LICENSE](LICENSE)



