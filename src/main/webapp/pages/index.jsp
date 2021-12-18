<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>

    #features-wrapper{
        padding:0px 30px 10px 30px;
        background-color: #3878E3;
    }
    #features-wrapper h1{
        color:#fff;
    }

    #splash-wrapper{
        margin:auto;
    }

    #splash{margin-bottom:-30px;}
    #splash img{
        z-index: 1;
        box-shadow: 0px 0px 101px rgba(0,0,0,0.29);
    }

    #features-wrapper{
        z-index: 21;
    }

    #features{
        padding:20px 0px;
        text-align: left;
        margin:0px auto;
        width:760px;
    }

    #features p{color:#fff}
    #features p,
    #friends p,
    #costs p{font-size:21px;font-weight: 300}

    #friends-wrapper{text-align: center}

    #splash{
        text-align: center;
        margin:0px auto;
        width:760px;
    }
    #friends{
        padding:20px 0px;
        width:760px;
        text-align: left;
        margin:0px auto;
        width:760px;
    }
    #friends p{width:53%}

</style>


<div id="splash-wrapper">
    <div id="splash" class="section">
        <h1 style="margin-bottom:10px;">Simple. Easy. Online Commerce.</h1>
        <img src="${pageContext.request.contextPath}/benefit/media/giga.splash.png" style="margin:auto;width:650px"/>
    </div>
</div>

<div id="features-wrapper">
    <div id="features">
<%--
stuart planned out his future
i got stuart money
i got stuart more money $3,000,000
i introduced him to a girl i was head over heals for
he got jealous over the attention and what i was going to get
so he toned it down by 90%. replanned my future. so i removed his future... he should be planning his own future inside anyways.
thats when he through a fit and started fucking every girl i knew inside while on the clock.
i guess stuart is ok.
--%>


        <h1>Features</h1>

        <p><strong>Multi-Business</strong> One or many shops, you decide. Running multiple shops at once on Giga is a breeze.</p>

        <p><strong>Multi-Design</strong> Completely customizable designs, and to boot Giga boasts a design per page, item and category.</p>

        <p><strong>Multi-Option</strong> Items can have multiple options or dimensions and variants with additional pricing. These can
            include things Size, Weight & Color.</p>

        <p><strong>Fully Functional Content Management System (CMS)</strong> Giga touts as a fully functional CMS system as well.
        If you want to run Giga as a plain site, it will do the job. </p>

        <p><strong>Simple Item Import</strong> Importing items should be easy, in previous attempts
            we fell short and relied on spreadsheets. With Giga, all you need is a little bit of
            up front work and items are easily imported on the fly, and we
            didn't rely on China to create it for us. Go USA!</p>

        <p><strong>Power Grid</strong> We took item maintenance and thoroughly thought it through,
            in turn we made a daunting task easy.</p>

        <p><strong>Ease of Maintenance</strong> We made sure to make shop maintenance a breeze. Give it a try!</p>

        <p><strong>Designed for Web Developers</strong> Giga aint no fancy drag and drop development system, it expects you to know how to design and write
        beautiful Html + Css + Javascript user experiences and allows you to do such.</p>

        <div class="align-center" style="margin:30px 0px 60px">
            <a href="${pageContext.request.contextPath}/signup" class="button yellow gigantic">Get Started as a Business!</a>
        </div>


    </div>
</div>

<div id="friends-wrapper">
    <div id="friends" class="section">
        <h1>Business<br/>Partner Program</h1>
        <p><strong>Say what? Business Partners?</strong>
            Better yet, friends program,
            if you like them so much why don't
            you marry em'.
            On Giga, you can pick a business
            or a product you are excited about and create an online
            store their behalf. You get a copy of all of their items &
            categories and set pricing on demand.</p>

        <p>As a business owner get excited about the idea of having
            people work on your behalf, we tried to make it as easy as possible
            giving you full control over the approval process.</p>

        <p style="width:39%; position: absolute;top:382px; right:0px;">
            There is no one best way to do something and we believe this
            to be true for the sales process as well. Which is
            why we are introducing the Affiliate/Partner program capability!</p>

        <p>Business owners can set a base commission rate on a case by case
            basis for your business partner.</p>

        <p>As a Business Partner, you get all of the above
            features, nothing hidden, just an agreement between
            you and the business you love.</p>

        <style>
            #friends{position: relative;}
            #friends a{position:absolute; right:50px; top:254px;}
        </style>

        <a href="${pageContext.request.contextPath}/affiliates/onboarding" class="button green gigantic">Get Started Now!</a>
    </div>
</div>

<style>
    #costs-wrapper{background:#FEF57A;text-align: center}
    #costs{background:#FEF57A;text-align: center;margin:auto;padding:30px 0px 70px 0px;width:760px;}
    #costs p{font-weight: 400}
    p.lightf{font-weight: 300 !important; width:400px;margin:auto;}
</style>

<div id="costs-wrapper">
    <div id="costs" class="section">
        <h1>Pricing</h1>
        <h1>4.21% of sales</h1>
        <p>42 cents on a $10 purchase!</p>
        <p class="lightf">
            Nothing more. No Monthly fees.<br/>
            You make money, we make money.
            We hope you enjoy!
        </p>
        <div class="align-center" style="margin:30px 0px 60px">
            <a href="${pageContext.request.contextPath}/signup" class="button light gigantic">Get Started as a Business!</a>
        </div>
    </div>
</div>

<%--<style>--%>
<%--    #shop-wrapper{text-align: center}--%>
<%--    #shop{width:760px;margin:20px auto 170px;}--%>
<%--</style>--%>
<%----%>
<%--<c:if test="${businesses.size() > 0}">--%>
<%--    <div id="shop-wrapper">--%>
<%--        <div id="shop">--%>
<%--            <h1>Shop <br/>Giga Shops!</h1>--%>
<%--            <p>The list below are all the shops on our platform.</p>--%>
<%--            <select id="businesses">--%>
<%--                <c:forEach items="${businesses}" var="business">--%>
<%--                    <option value="${business.uri}">${business.name}</option>--%>
<%--                </c:forEach>--%>
<%--            </select>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</c:if>--%>
<%----%>
<%--<script>--%>
<%--    let select = document.getElementById("businesses")--%>
<%--    select.addEventListener("change", function(){--%>
<%--        window.location = "${pageContext.request.contextPath}/" + select.value--%>
<%--    })--%>
<%--</script>--%>

