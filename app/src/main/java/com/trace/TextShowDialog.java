package com.trace;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 文字内容提示弹窗
 *
 * @author guanzhong
 * @since 2018/7/26
 */
public class TextShowDialog extends Dialog {
    public static final String BTN_TYPE_I_KNOW = "i_know";
    public static final String BTN_TYPE_SURE_CANCEL = "sure_cancel";
    public static final String BTN_TYPE_BLUE = "blue";
    public static final String BTN_TYPE_WHITE = "white";

    public TextShowDialog(@NonNull Context context) {
        super(context);
    }

    public TextShowDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected TextShowDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Build {
        private Context context;
        private String title;
        private String content;
        private String btnType = BTN_TYPE_SURE_CANCEL;
        private String leftBtn = "", rightBtn = "", knowBtn = "";
        private String knowType;
        private boolean showImage = true;
        private OnClickListener iKnowListener;
        private OnClickListener confirmListener;
        private OnClickListener cancelListener;
        private OnKeyListener onKeyListener;

        public Build(Context context) {
            this.context = context;
        }

        /**
         * @param title 标题
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * @param content 内容
         */
        public void setContent(String content) {
            this.content = content;
        }

        /**
         * @param btnType 按钮类型：我知道了/确定&取消
         */
        public void setBtnType(String btnType) {
            this.btnType = btnType;
        }

        /**
         * @param iKnowListener 我知道了点击事件
         */
        public void setiKnowListener(OnClickListener iKnowListener) {
            this.iKnowListener = iKnowListener;
        }

        /**
         * @param confirmListener 右侧蓝色按钮点击事件
         */
        public void setConfirmListener(OnClickListener confirmListener) {
            this.confirmListener = confirmListener;
        }

        /**
         * @param cancelListener 左侧白色按钮点击事件
         */
        public void setCancelListener(OnClickListener cancelListener) {
            this.cancelListener = cancelListener;
        }

        /**
         * @param onKeyListener dialog的物理返回键监听
         */
        public void setOnKeyListener(OnKeyListener onKeyListener) {
            this.onKeyListener = onKeyListener;
        }

        /**
         * @param type 单个按钮时 设置按钮颜色类型
         */
        public void setColorType(String type) {
            knowType = type;
        }

        /**
         * @param showImage 是否显示顶部图片
         */
        public void setShowImage(boolean showImage) {
            this.showImage = showImage;
        }

        public TextShowDialog creat() {
            final TextShowDialog dialog = new TextShowDialog(context, R.style.nifty_dialog_untran);
            View dialogView = LayoutInflater.from(context).inflate(R.layout.view_text_show_dialog, null);
            dialog.addContentView(dialogView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //默认不可取消
            dialog.setCanceledOnTouchOutside(false);
            TextView titleTv = (TextView) dialogView.findViewById(R.id.text_dialog_title);
            TextView contentTv = (TextView) dialogView.findViewById(R.id.text_dialog_content);
            TextView iKonwTv = (TextView) dialogView.findViewById(R.id.text_dialog_i_know);
            LinearLayout sureCancelLy = (LinearLayout) dialogView.findViewById(R.id.text_dialog_sure_cancel);
            TextView cancelTv = (TextView) dialogView.findViewById(R.id.text_dialog_cancel);
            TextView confirmTv = (TextView) dialogView.findViewById(R.id.text_dialog_confirm);
            //我知道了
            if (btnType.equals(BTN_TYPE_I_KNOW)) {
                iKonwTv.setVisibility(View.VISIBLE);
                sureCancelLy.setVisibility(View.GONE);
                iKonwTv.setText(knowBtn);
                //确定/取消
            } else if (btnType.equals(BTN_TYPE_SURE_CANCEL)) {
                iKonwTv.setVisibility(View.GONE);
                sureCancelLy.setVisibility(View.VISIBLE);
                cancelTv.setText(leftBtn);
                confirmTv.setText(rightBtn);
            }
            titleTv.setText(title);
            contentTv.setText(content);
            iKonwTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (iKnowListener != null) {
                        iKnowListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                    }
                }
            });
            confirmTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (confirmListener != null) {
                        confirmListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                    }
                }
            });
            cancelTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (cancelListener != null) {
                        cancelListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                    }
                }
            });
            if (onKeyListener != null) {
                dialog.setOnKeyListener(onKeyListener);
            }
            return dialog;
        }
    }
}