package com.sds.android.sdk.lib.fakebuild;

import com.sds.android.cloudapi.ttpod.result.OnlineMusicCategoryResult;

import java.util.ArrayList;
import java.util.Random;

public class FakeOnlineMusicCategoryResult extends BaseFakeResult<OnlineMusicCategoryResult>{
    private int index = 0;
    @Override
    public OnlineMusicCategoryResult build() {
        index = 0;
        OnlineMusicCategoryResult onlineMusicCategoryResult = new OnlineMusicCategoryResult();
        onlineMusicCategoryResult.setCode(1);
        onlineMusicCategoryResult.setmTTL(10*1000);
        ArrayList<OnlineMusicCategoryResult.CategoryData> categoryDataArrayList = new ArrayList<>();

        categoryDataArrayList.add(getCategoryData("夜店"));
        categoryDataArrayList.add(getCategoryData("学习工作"));
        categoryDataArrayList.add(getCategoryData("咖啡馆"));
        categoryDataArrayList.add(getCategoryData("运动"));
        categoryDataArrayList.add(getCategoryData("睡前"));
        categoryDataArrayList.add(getCategoryData("旅行"));
        categoryDataArrayList.add(getCategoryData("跳舞"));
        categoryDataArrayList.add(getCategoryData("派对"));
        categoryDataArrayList.add(getCategoryData("婚礼"));
        categoryDataArrayList.add(getCategoryData("约会"));
        categoryDataArrayList.add(getCategoryData("校园"));
        categoryDataArrayList.add(getCategoryData("驾驶"));
        categoryDataArrayList.add(getCategoryData("伤感"));
        categoryDataArrayList.add(getCategoryData("快乐"));
        categoryDataArrayList.add(getCategoryData("安静"));
        categoryDataArrayList.add(getCategoryData("励志"));
        categoryDataArrayList.add(getCategoryData("治愈"));
        categoryDataArrayList.add(getCategoryData("思念"));
        categoryDataArrayList.add(getCategoryData("甜蜜"));
        categoryDataArrayList.add(getCategoryData("寂寞"));
        categoryDataArrayList.add(getCategoryData("宣泄"));
        onlineMusicCategoryResult.setmCategoryList(categoryDataArrayList);
        onlineMusicCategoryResult.setmPages(1);
        onlineMusicCategoryResult.setmRows(3);
        return onlineMusicCategoryResult;
    }

    private OnlineMusicCategoryResult.CategoryData getCategoryData(String name){
        index++;
        OnlineMusicCategoryResult.CategoryData categoryData = new OnlineMusicCategoryResult.CategoryData();
        categoryData.setmId(index);
        categoryData.setmName(name);
        categoryData.setmCount(new Random().nextInt());
        return categoryData;
    }
}
