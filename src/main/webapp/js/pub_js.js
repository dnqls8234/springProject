$(function(){
    
    //gnb
    $('.gnb_opener').on('click',function(){
        $('.gnb_wrap').show().stop().animate({right:'0'}, 200, 'swing');
    });
    $('.gnb_closer').on('click',function(){
        $('.gnb_wrap').stop().animate({right:'-100%'}, 100, 'swing', function(){
            $('.gnb_wrap').hide();
        });
    });
    $('.plus').on('click',function(){
        $(this).children('.gm_sub').slideToggle();
    });
    $('.flash').on('click',function(){
        if($(this).hasClass('off')){
            $(this).removeClass('off').children('img').attr('src', '../images/flash_on.png');
        } else {
            $(this).addClass('off').children('img').attr('src', '../images/flash_off.png');
        }
    });
    
    //fixed_bottom
    if($('.content').children('div').hasClass('fixed_bottom')){
       $('.content').addClass('pb30');
    } else {
        $('.content').removeClass('pb30');
    }
    if($('.popup_wrap').children('div').hasClass('fixed_bottom')){
       $('.fixed_bottom').parent('.popup_wrap').addClass('pb30');
    } else {
        $('.fixed_bottom').parent('.popup_wrap').removeClass('pb30');
    }

    //테이블 아코디언
    $('.table_accordion').on('click',function(){
        if($(this).hasClass('on')){
            $(this).removeClass('on').addClass('off').text('열기').prev('.accordion_area').stop().animate({height:'32px'}, 200, 'swing');
        } else {
            $(this).removeClass('off').addClass('on').text('접기').prev('.accordion_area').css({height:'100%'})

        }
    });
    $('.ta_ac').on('click',function(){
        $('.table_accordion').removeClass('on').addClass('off').text('열기').prev('.accordion_area').stop().animate({height:'32px'}, 200, 'swing');
    });
    
    //사용전점검 - 점검입력
    $('.client_btn').on('click',function(){
        if($(this).hasClass('client_closer')){
            $(this).removeClass('client_closer').addClass('client_opener').children('img').attr('src', '../images/arrow_down_blue.png');
            $('.client_area').stop().animate({height:'0', opacity:'0'}, 200, 'swing', function(){
                $('.client_area').removeClass('pr15 pl15 pb25').css({padding:'5px'});
            });
        } else {
            $(this).removeClass('client_opener').addClass('client_closer').children('img').attr('src', '../images/arrow_up_blue.png');
            $('.client_area').addClass('pr15 pl15 pb25').stop().animate({height:'100%', opacity:'1'}, 200, 'swing');

        }
    });
    
    //팝업
    $('.popup_closer').on('click',function(){
        $('.popup_wrap').hide();
        $('.popup_bg').hide();
    });
    //팝업 - 지도
    $('.map_opener').on('click',function(){
        $('.map_popup').show();
    });
    //팝업 - 사용전점검 - 가이드
    $('.guide_opener').on('click',function(){
        $('.guide_popup').show();
    });
    //팝업 - 사용전점검 - 점검이력 & 정기점검 - 점검이력
    $('.history_opener').on('click',function(){
        $('.history_popup').show();
    });
    //팝업 - 사용전점검 - 메모
    $('.memo_opener').on('click',function(){
        $('.popup_bg').show();
        $('.memo_popup').show();
    });
    //팝업 - 사용전점검 - 과거이력불러오기
    $('.load_opener').on('click',function(){
        $('.popup_bg').show();
        $('.load_popup').show();
    });
    //팝업 - 정기점검 - 신청점검접수
    $('.register_opener').on('click',function(){
        $('.popup_bg').show();
        $('.register_popup').show();
    });
    //팝업 - 정기점검 - 일괄처리
    $('.batch_opener').on('click',function(){
        $('.popup_bg').show();
        $('.batch_popup').show();
    });
    //팝업 - 정기점검 - 전산외생성
    $('.create_opener').on('click',function(){
        $('.popup_bg').show();
        $('.create_popup').show();
    });
    //팝업 - 정기점검 - 전산외삭제
    $('.delete_opener').on('click',function(){
        $('.popup_bg').show();
        $('.delete_popup').show();
    });
    //팝업 - 정기점검 - 설비입력
    $('.facilityinput_opener').on('click',function(){
        $('.popup_bg').show();
        $('.facilityinput_popup').show();
    });
    //팝업 - 정기점검 - 주소검색
    $('.address_opener').on('click',function(){
        $('.popup_bg').show();
        $('.address_popup').show();
    });
    //팝업 - 정기점검 - 앨범
    $('.gallery_opener').on('click',function(){
        $('.popup_bg').show();
        $('.gallery_popup').show();
    });
    //팝업 - 정기점검 - 시설개선
    $('.facility_opener').on('click',function(){
        $('.popup_bg').show();
        $('.facility_popup').show();
    });
    //팝업 - 정기점검 - 정보이용동의서
    $('.consent_opener').on('click',function(){
        $('.popup_bg').show();
        $('.consent_popup').show();
    });
    //팝업 - 정기점검 - 지자체설비
    $('.local_opener').on('click',function(){
        $('.popup_bg').show();
        $('.local_popup').show();
    });
    //팝업 - 정기점검 - 계측기활용실적
    $('.measure_opener').on('click',function(){
        $('.popup_bg').show();
        $('.measure_popup').show();
    });
    //팝업 - 신청점검 - 신청취소
    $('.cancel_opener').on('click',function(){
        $('.popup_bg').show();
        $('.cancel_popup').show();
    });
    //팝업 - 신청점검 - 담당자변경
    $('.contact_opener').on('click',function(){
        $('.popup_bg').show();
        $('.contact_popup').show();
    });
    //팝업 - 신청점검 - 예정일변경
    $('.dday_opener').on('click',function(){
        $('.popup_bg').show();
        $('.dday_popup').show();
    });
    //팝업 - 안전진단 - 개선의견
    $('.improvement_opener').on('click',function(){
        $('.popup_bg').show();
        $('.improvement_popup').show();
    });
    //팝업 - 안전진단 - 종합의견
    $('.synthesize_opener').on('click',function(){
        $('.popup_bg').show();
        $('.synthesize_popup').show();
    });
    //팝업 - 안전진단 - 보고서
    $('.report_opener').on('click',function(){
        $('.popup_bg').show();
        $('.report_popup').show();
    });
    
    //정기점검 - 업무통계
    $('.tab button').on('click',function(){
        var Tab = $(this).attr('data-tab');
        $('.tab button').removeClass('btnc_main').addClass('btnc_light2');
        $('.tab_wrap').css({"display":"none"});
        $(this).removeClass('btnc_light2').addClass('btnc_main');
        $('.' + Tab).css({"display":"block"});
    });
});