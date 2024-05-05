/*global jQuery */
/* Contents
// ------------------------------------------------>
	1.  BACKGROUND INSERT
	2.  MOBILE MENU
	3.	HEADER AFFIX
	4.  COUNTDOWN DATE
	5.  AJAX MAILCHIMP
	6.  AJAX CAMPAIGN MONITOR 
	7.  OWL CAROUSEL
	8.  MAGNIFIC POPUP
	9.  MAGNIFIC POPUP VIDEO
	10. SCROLL TO
	11. PROGRESS BAR
	
*/
(function($) {
    "use strict";

    /* ------------------  Background INSERT ------------------ */

    var $bgSection = $(".bg-section");
    var $bgPattern = $(".bg-pattern");
    var $colBg = $(".col-bg");

    $bgSection.each(function() {
        var bgSrc = $(this).children("img").attr("src");
        var bgUrl = 'url(' + bgSrc + ')';
        $(this).parent().css("backgroundImage", bgUrl);
        $(this).parent().addClass("bg-section");
        $(this).remove();
    });

    $bgPattern.each(function() {
        var bgSrc = $(this).children("img").attr("src");
        var bgUrl = 'url(' + bgSrc + ')';
        $(this).parent().css("backgroundImage", bgUrl);
        $(this).parent().addClass("bg-pattern");
        $(this).remove();
    });

    $colBg.each(function() {
        var bgSrc = $(this).children("img").attr("src");
        var bgUrl = 'url(' + bgSrc + ')';
        $(this).parent().css("backgroundImage", bgUrl);
        $(this).parent().addClass("col-bg");
        $(this).remove();
    });


    /* ------------------  MOBILE MENU ------------------ */

    var $dropToggle = $("ul.dropdown-menu [data-toggle=dropdown]"),
        $module = $(".module");
    $dropToggle.on("click", function(event) {
        event.preventDefault();
        event.stopPropagation();
        $(this).parent().siblings().removeClass("open");
        $(this).parent().toggleClass("open");
    });

    /* ------------------ HEADER AFFIX ------------------ */

    var $navAffix = $(".header-fixed .navbar-fixed-top");
    $navAffix.affix({
        offset: {
            top: 50
        }
    });


    /* ------------------ COUNTDOWN DATE ------------------ */

    $(".countdown").each(function() {
        var $countDown = $(this),
            countDate = $countDown.data("count-date"),
            newDate = new Date(countDate);
        $countDown.countdown({
            until: newDate,
            format: "MMMM Do , h:mm:ss a"
        });
    });

    /* ------------------  AJAX MAILCHIMP ------------------ */

    $('.mailchimp').ajaxChimp({
        url: "http://wplly.us5.list-manage.com/subscribe/post?u=91b69df995c1c90e1de2f6497&id=aa0f2ab5fa", //Replace with your own mailchimp Campaigns URL.
        callback: chimpCallback

    });

    function chimpCallback(resp) {
        if (resp.result === 'success') {
            $('.subscribe-alert').html('<div class="alert alert-success">' + resp.msg + '</div>').fadeIn(1000);
            //$('.subscribe-alert').delay(6000).fadeOut();

        } else if (resp.result === 'error') {
            $('.subscribe-alert').html('<div class="alert alert-danger">' + resp.msg + '</div>').fadeIn(1000);
        }
    }

    /* ------------------  AJAX CAMPAIGN MONITOR  ------------------ */

    $('#campaignmonitor').submit(function(e) {
        e.preventDefault();
        $.getJSON(
            this.action + "?callback=?",
            $(this).serialize(),
            function(data) {
                if (data.Status === 400) {
                    alert("Error: " + data.Message);
                } else { // 200
                    alert("Success: " + data.Message);
                }
            });
    });

    /* ------------------ OWL CAROUSEL ------------------ */

    $(".carousel").each(function() {
        var $Carousel = $(this);
        $Carousel.owlCarousel({
            loop: $Carousel.data('loop'),
            autoplay: $Carousel.data("autoplay"),
            margin: $Carousel.data('space'),
            nav: $Carousel.data('nav'),
            dots: $Carousel.data('dots'),
            center: $Carousel.data('center'),
            dotsSpeed: $Carousel.data('speed'),
            responsive: {
                0: {
                    items: 1,
                },
                600: {
                    items: $Carousel.data('slide-rs'),
                },
                1000: {
                    items: $Carousel.data('slide'),
                }
            }
        });
    });

    /* ------------------ MAGNIFIC POPUP ------------------ */

    var $imgPopup = $(".img-popup");
    $imgPopup.magnificPopup({
        type: "image"
    });
    $('.img-gallery-item').magnificPopup({
        type: 'image',
        gallery: {
            enabled: true
        }
    });
    /* ------------------  MAGNIFIC POPUP VIDEO ------------------ */

    $('.popup-video,.popup-gmaps').magnificPopup({
        disableOn: 700,
        mainClass: 'mfp-fade',
        removalDelay: 0,
        preloader: false,
        fixedContentPos: false,
        type: 'iframe',
        iframe: {
            markup: '<div class="mfp-iframe-scaler">' +
                '<div class="mfp-close"></div>' +
                '<iframe class="mfp-iframe" frameborder="0" allowfullscreen></iframe>' +
                '</div>',
            patterns: {
                youtube: {
                    index: 'youtube.com/',
                    id: 'v=',
                    src: '//www.youtube.com/embed/%id%?autoplay=1'
                }
            },
            srcAction: 'iframe_src',
        }
    });

    /* ------------------ PORTFOLIO FLITER ------------------ */

    var $portfolioFilter = $(".portfolio-filter"),
        portfolioLength = $portfolioFilter.length,
        protfolioFinder = $portfolioFilter.find("a"),
        $portfolioAll = $("#portfolio-all");

    // init Isotope For portfolio
    protfolioFinder.on("click", function(e) {
        e.preventDefault();
        $portfolioFilter.find("a.active-filter").removeClass("active-filter");
        $(this).addClass("active-filter");
    });
    if (portfolioLength > 0) {
        $portfolioAll.imagesLoaded().progress(function() {
            $portfolioAll.isotope({
                filter: "*",
                animationOptions: {
                    duration: 750,
                    itemSelector: ".portfolio-item",
                    easing: "linear",
                    queue: false,
                }
            });
        });
    }
    protfolioFinder.on("click", function(e) {
        e.preventDefault();
        var $selector = $(this).attr("data-filter");
        $portfolioAll.imagesLoaded().progress(function() {
            $portfolioAll.isotope({
                filter: $selector,
                animationOptions: {
                    duration: 750,
                    itemSelector: ".portfolio-item",
                    easing: "linear",
                    queue: false,
                }
            });
            return false;
        });
    });

    /* ------------------  SCROLL TO ------------------ */

    var aScroll = $('a[data-scroll="scrollTo"]');
    aScroll.on('click', function(event) {
        var target = $($(this).attr('href'));
        if (target.length) {
            event.preventDefault();
            $('html, body').animate({
                scrollTop: target.offset().top - 100
            }, 1000);
            if ($(this).hasClass("menu-item")) {
                $(this).parent().addClass("active");
                $(this).parent().siblings().removeClass("active");
            }
        }
    });

    /* ------------------ PROGRESS BAR ------------------ */
    if ($(".skills").length > 0) {
        $(window).scroll(function() {
            var skillsTop = $(".skills").offset().top - 50,
                skillsHight = $(this).outerHeight(),
                wScroll = $(window).scrollTop();
            if (wScroll > skillsTop - 1 && wScroll < skillsTop + skillsHight - 1) {
                $(".progress-bar").each(function() {
                    $(this).width($(this).attr('aria-valuenow') + '%');
                });
            }
        });
    }

    if ($(".skills-scroll").length > 0) {
        $(".progress-bar").each(function() {
            $(this).width($(this).attr('aria-valuenow') + '%');
        });
    }

    /* ------------------ NAV SPLIT ------------------ */

    if ($('.body-scroll').length > 0) {
        $(window).on("scroll", function() {
            $('.section').each(function() {
                var sectionID = $(this).attr("id"),
                    sectionTop = $(this).offset().top - 100,
                    sectionHight = $(this).outerHeight(),
                    wScroll = $(window).scrollTop(),
                    $navHref = $("a[href='#" + sectionID + "']"),
                    $nav = $('.nav-split').find($navHref).parent();
                if (wScroll > sectionTop - 1 && wScroll < sectionTop + sectionHight - 1) {
                    $nav.addClass('active');
                    $nav.siblings().removeClass('active');
                }
            });
        });
    }

    /* ------------------ GOOGLE MAP ------------------ */
    $(".googleMap").each(function() {
        var $gmap = $(this);
        $gmap.gMap({
            address: $gmap.data('map-address'),
            zoom: $gmap.data('map-zoom'),
            maptype: $gmap.data('map-type'),
            markers: [{
                address: $gmap.data('map-address'),
                maptype: $gmap.data('map-type'),
                html: $gmap.data('map-info'),
                icon: {
                    image: "assets/images/gmap/maker.png",
                    iconsize: [41, 54],
                    iconanchor: [41, 54]
                }
            }]
        });

    });

}(jQuery));