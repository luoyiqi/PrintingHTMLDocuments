package skillbooting.printinghtmldocuments;
//http://developer.android.com/training/printing/html-docs.html
import android.content.Context;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintJobInfo;
import android.print.PrintManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public WebView mWebView;
    public static String TAG = "Page Finished";
    public List<PrintJobInfo> mPrintJobs;
    int button_id;
    String jobName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPrintJobs=new ArrayList<PrintJobInfo>();
    }

    public void print(View v)
    {
        button_id=v.getId();
        doWebViewPrint();
    }
    //Printing an HTML document with WebView involves loading an HTML resource or building an HTML document as a string.
    private void doWebViewPrint() {
        // Create a WebView object specifically for printing
        WebView webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "page finished loading " + url);
                createWebPrintJob(view);
                mWebView = null;
            }
        });

        if(button_id==R.id.button1)
        {
            // Generate an HTML document on the fly:
            String htmlDocument = "<html><body><h1>Test Content</h1><p>Testing, " +
                    "testing, testing...</p></body></html>";
            webView.loadDataWithBaseURL(null, htmlDocument, "text/HTML", "UTF-8", null);
            jobName="WebViewBasicText"+"Document";
        }
        else
        /**
         * If you want to include graphics in the page, place the graphic files in the assets/ directory of your project
         * and specify a base URL in the first parameter of the loadDataWithBaseURL() method, as shown in the following code example:
         * webView.loadDataWithBaseURL("file:///android_asset/images/", htmlBody,"text/HTML", "UTF-8", null);
         **/
        if(button_id==R.id.button2)
        {
            String htmlBody="<html><body><h1>Test Content</h1><p>Testing, " ;
            webView.loadDataWithBaseURL("file:///android_asset/logo.png", htmlBody,"text/HTML", "UTF-8", null);
            jobName="WebViewTextImage"+"Document";
        }

        /**
         * You can also load a web page for printing by replacing the loadDataWithBaseURL() method with loadUrl() as shown below.
         * Print an existing web page (remember to request INTERNET permission!):
         * webView.loadUrl("http://developer.android.com/about/index.html");
         */
        else
        {
            webView.loadUrl("http://developer.android.com/about/index.html");
            jobName="WebViewHTML"+"Document";
        }

        // Keep a reference to WebView object until you pass the PrintDocumentAdapter
        // to the PrintManager
        mWebView = webView;
    }




    private void createWebPrintJob(WebView webView) {

        // Get a PrintManager instance
        PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);

        // Get a print adapter instance
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter();

        // Create a print job with name and adapter instance
        //String jobName = getString(R.string.app_name) + " Document";
        PrintJob printJob = printManager.print(jobName, printAdapter, new PrintAttributes.Builder().build());


        //Save the job object for later status checking
        //if(printJob!=null)
        //mPrintJobs.add(printJob.getInfo());

        /**This example saves the info of instance of the PrintJob object for use by the application, which is not required.
         * Your application may use this object to track the progress of the print job as it's being processed.
         * This approach is useful when you want to monitor the status of the print job
         * in you application for completion, failure, or user cancellation.

         */
    }

    //Creating an in-app notification is not required, because the print framework automatically creates a system notification for the print job.


}

