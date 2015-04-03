package org.wicket.ssl.pages.mountedHttps;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.https.RequireHttps;
import org.wicketstuff.annotation.mount.MountPath;

@RequireHttps
@MountPath("/mounted/https")
public class MountedHttpsPage extends WebPage
{

}
