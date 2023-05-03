(function($) {
  "use strict"

  // NAVIGATION
  var responsiveNav = $('#navigation #responsive-nav'),
    catToggle = $('#navigation #responsive-nav .category-nav .category-header'),
    catList = $('#navigation #responsive-nav .category-nav .category-list'),
    menuToggle = $('#navigation #responsive-nav .menu-nav .menu-header'),
    menuList = $('#navigation #responsive-nav .menu-nav .menu-list');

  catToggle.on('click', function() {
    menuList.removeClass('open');
    catList.toggleClass('open');
  });

  menuToggle.on('click', function() {
    catList.removeClass('open');
    menuList.toggleClass('open');
  });

  $(document).click(function(event) {
    if (!$(event.target).closest(responsiveNav).length) {
      if (responsiveNav.hasClass('open')) {
        responsiveNav.removeClass('open');
        $('#navigation').removeClass('shadow');
      } else {
        if ($(event.target).closest('.nav-toggle > button').length) {
          if (!menuList.hasClass('open') && !catList.hasClass('open')) {
            menuList.addClass('open');
          }
          $('#navigation').addClass('shadow');
          responsiveNav.addClass('open');
        }
      }
    }
  });

  // HOME SLICK
  $('#home-slick').slick({
    autoplay: true,
    infinite: true,
    speed: 300,
    arrows: true,
  });

  // PRODUCTS SLICK
  $('#product-slick-1').slick({
    slidesToShow: 3,
    slidesToScroll: 2,
    autoplay: true,
    infinite: true,
    speed: 300,
    dots: true,
    arrows: false,
    appendDots: '.product-slick-dots-1',
    responsive: [{
        breakpoint: 991,
        settings: {
          slidesToShow: 1,
          slidesToScroll: 1,
        }
      },
      {
        breakpoint: 480,
        settings: {
          dots: false,
          arrows: true,
          slidesToShow: 1,
          slidesToScroll: 1,
        }
      },
    ]
  });

  $('#product-slick-2').slick({
    slidesToShow: 3,
    slidesToScroll: 2,
    autoplay: true,
    infinite: true,
    speed: 300,
    dots: true,
    arrows: false,
    appendDots: '.product-slick-dots-2',
    responsive: [{
        breakpoint: 991,
        settings: {
          slidesToShow: 1,
          slidesToScroll: 1,
        }
      },
      {
        breakpoint: 480,
        settings: {
          dots: false,
          arrows: true,
          slidesToShow: 1,
          slidesToScroll: 1,
        }
      },
    ]
  });

  // PRODUCT DETAILS SLICK
  $('#product-main-view').slick({
    infinite: true,
    speed: 300,
    dots: false,
    arrows: true,
    fade: true,
    asNavFor: '#product-view',
  });

  $('#product-view').slick({
    slidesToShow: 3,
    slidesToScroll: 1,
    arrows: true,
    centerMode: true,
    focusOnSelect: true,
    asNavFor: '#product-main-view',
  });

  // PRODUCT ZOOM
  $('#product-main-view .product-view').zoom();

  // PRICE SLIDER
  var slider = document.getElementById('price-slider');
  if (slider) {
    noUiSlider.create(slider, {
      start: [1, 999],
      connect: true,
      tooltips: [true, true],
      format: {
        to: function(value) {
          return value.toFixed(2) + '$';
        },
        from: function(value) {
          return value
        }
      },
      range: {
        'min': 1,
        'max': 999
      }
    });
  }

})(jQuery);

//scroll menu

  var responsiveNav = $('#scrolled-header #responsive-nav'),
    catToggle = $('#scrolled-header #responsive-nav .category-nav .category-header'),
    catList = $('#scrolled-header #responsive-nav .category-nav .category-list'),
    menuToggle = $('#scrolled-header #responsive-nav .menu-nav .menu-header'),
    menuList = $('#scrolled-header #responsive-nav .menu-nav .menu-list');

  catToggle.on('click', function() {
    menuList.removeClass('open');
    catList.toggleClass('open');
  });

  menuToggle.on('click', function() {
    catList.removeClass('open');
    menuList.toggleClass('open');
  });
  
let scrolled_header = document.querySelector('#scrolled-header');
let navigation = document.querySelector('#navigation');

function scrollMenu() {
    window.addEventListener('scroll',function () {
        x = window.pageYOffset;
        if (x >= navigation.offsetTop - navigation.offsetHeight){
            if(!scrolled_header.classList.contains('active')){
                scrolled_header.classList.add('active');
            }
        } else{
		    if(scrolled_header.classList.contains('active')){
                scrolled_header.classList.remove('active');
            }	
		}
        
    })
}
scrollMenu()


//back to top
let backtotop = document.querySelector('.backtotop');
backtotop.addEventListener('click', function (backtotop) {
    backtotop.preventDefault();
    window.scrollTo({
        top: 0,
        behavior: 'smooth'
    })
})