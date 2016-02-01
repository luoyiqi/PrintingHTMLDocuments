# PrintingHTMLDocuments
How to quickly build an HTML document containing text and graphics and use WebView to print it (Part of the series Mastering Android)

- Dependencies and prerequisites : Android 4.4 (API Level 19) or higher
- The Android framework provides a way to use HTML to compose a document and print it with a minimum of code.
- In Android 4.4 (API level 19), the WebView class has been updated to enable printing HTML content. The class allows you to load a local HTML resource or download a page from the web, create a print job and hand it off to Android's print services.

Possible Error

- It is possible that when you click the print button you get "Unfortunately, Print Spooler has stopped". In such a case, the only solution I seem to have come accross is to clear the cache of Print Spooler in the device and then try again.
