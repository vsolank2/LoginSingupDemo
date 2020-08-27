package com.swatiitsolutions.loginsingupdemo.Api;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.swatiitsolutions.loginsingupdemo.Login.ClsLoginResponse;
import com.swatiitsolutions.loginsingupdemo.R;
import com.swatiitsolutions.loginsingupdemo.SingIn.ClsSingInResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private Context context;
    private SharedPreferences sharedPreferences;

    public Repository(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
    }


    public LiveData<ClsSingInResponse> postCustomer(String flag, String customer_name, String mobile, String email, String address, String password, String user_type) {

        final MutableLiveData<ClsSingInResponse> customerResponse = new MutableLiveData<>();
        InterfaceCustomer interfaceCustomer = ApiClient.getRetrofitInstance().create(InterfaceCustomer.class);

        Call<ClsSingInResponse> call = interfaceCustomer.postCustomer(flag, customer_name, mobile, email, address, password, user_type);

        call.enqueue(new Callback<ClsSingInResponse>() {
            @Override
            public void onResponse(Call<ClsSingInResponse> call, Response<ClsSingInResponse>
                    response) {

                if (response.body() != null) {
                    customerResponse.postValue(response.body());
                } else {
                    Toast.makeText(context, R.string.somethingWrong, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ClsSingInResponse> call, Throwable t) {
                try {
                    customerResponse.postValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return customerResponse;
    }

    public LiveData<ClsLoginResponse> login(String mobile, String password, String fcmToken) {

        final MutableLiveData<ClsLoginResponse> customerResponse = new MutableLiveData<>();
        InterfaceCustomer interfaceCustomer = ApiClient.getRetrofitInstance().create(InterfaceCustomer.class);

        Call<ClsLoginResponse> call = interfaceCustomer.login(mobile, password, fcmToken);

        call.enqueue(new Callback<ClsLoginResponse>() {
            @Override
            public void onResponse(Call<ClsLoginResponse> call, Response<ClsLoginResponse>
                    response) {

                if (response.body() != null) {
                    customerResponse.postValue(response.body());
                } else {
                    Toast.makeText(context, R.string.somethingWrong, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ClsLoginResponse> call, Throwable t) {
                try {
                    customerResponse.postValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return customerResponse;
    }



/*
    public LiveData<ClsCustomerGetResponse> getCustomer(String flag) {

        final MutableLiveData<ClsCustomerGetResponse> customerResponseList = new MutableLiveData<>();
        InterfaceCustomer interfaceWaiting = ApiClient.getRetrofitInstance().create(InterfaceCustomer.class);
        Call<ClsCustomerGetResponse> call = interfaceWaiting.getCustomer(flag);

        call.enqueue(new Callback<ClsCustomerGetResponse>() {
            @Override
            public void onResponse(Call<ClsCustomerGetResponse> call, Response<ClsCustomerGetResponse> response) {
                if (response.body() != null && response.code() == 200) {

                    customerResponseList.setValue(response.body());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                }
            }

            @Override
            public void onFailure(Call<ClsCustomerGetResponse> call, Throwable t) {

                try {
                    customerResponseList.setValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return customerResponseList;
    }
*/




/*
    public LiveData<ClsCustomerResponse> updateCustomer(String flag, int id, String customer_name, String mobile, String email, String address, String password) {

        final MutableLiveData<ClsCustomerResponse> customerResponse = new MutableLiveData<>();
        InterfaceCustomer interfaceCustomer = ApiClient.getRetrofitInstance().create(InterfaceCustomer.class);


        Call<ClsCustomerResponse> call = interfaceCustomer.updateCustomer(flag, id, customer_name, mobile, email, address, password);


        call.enqueue(new Callback<ClsCustomerResponse>() {
            @Override
            public void onResponse(Call<ClsCustomerResponse> call, Response<ClsCustomerResponse>
                    response) {

                if (response.body() != null) {
                    customerResponse.postValue(response.body());
                } else {
                    Toast.makeText(context, R.string.somethingWrong, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClsCustomerResponse> call, Throwable t) {
                try {
                    customerResponse.postValue(null);

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });

        return customerResponse;
    }
*/



   /* public LiveData<ClsCustomerOrdersResponse> getCustomerOrders(int cId) {

        final MutableLiveData<ClsCustomerOrdersResponse> OrdersResponseList = new MutableLiveData<>();
        InterfaceItemInfo interfaceItemInfo = ApiClient.getRetrofitInstance().create(InterfaceItemInfo.class);
        Call<ClsCustomerOrdersResponse> call = interfaceItemInfo.getOrders(cId);


        call.enqueue(new Callback<ClsCustomerOrdersResponse>() {
            @Override
            public void onResponse(Call<ClsCustomerOrdersResponse> call, Response<ClsCustomerOrdersResponse> response) {

                if (response.body() != null && response.code() == 200) {

                    OrdersResponseList.setValue(response.body());


                }
            }

            @Override
            public void onFailure(Call<ClsCustomerOrdersResponse> call, Throwable t) {

                try {
                    OrdersResponseList.setValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return OrdersResponseList;
    }

    public LiveData<ClsPendingOrdersResponse> getPendingOrders() {

        final MutableLiveData<ClsPendingOrdersResponse> OrdersResponseList = new MutableLiveData<>();
        InterfaceOwner interfaceOwner = ApiClient.getRetrofitInstance().create(InterfaceOwner.class);
        Call<ClsPendingOrdersResponse> call = interfaceOwner.getPendingOrders();


        call.enqueue(new Callback<ClsPendingOrdersResponse>() {
            @Override
            public void onResponse(Call<ClsPendingOrdersResponse> call, Response<ClsPendingOrdersResponse> response) {

                if (response.body() != null && response.code() == 200) {

                    OrdersResponseList.setValue(response.body());


                }
            }

            @Override
            public void onFailure(Call<ClsPendingOrdersResponse> call, Throwable t) {

                try {
                    OrdersResponseList.setValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return OrdersResponseList;
    }

    public LiveData<ClsOrdersStatusResponse> ordersStatusChange(String order_id, String order_status) {

        final MutableLiveData<ClsOrdersStatusResponse> customerResponse = new MutableLiveData<>();
        InterfaceOwner interfaceOwner = ApiClient.getRetrofitInstance().create(InterfaceOwner.class);


        Call<ClsOrdersStatusResponse> call = interfaceOwner.changeStatus(order_id, order_status);
        call.enqueue(new Callback<ClsOrdersStatusResponse>() {
            @Override
            public void onResponse(Call<ClsOrdersStatusResponse> call, Response<ClsOrdersStatusResponse>
                    response) {

                if (response.body() != null) {
                    customerResponse.postValue(response.body());
                } else {
                    Toast.makeText(context, R.string.somethingWrong, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClsOrdersStatusResponse> call, Throwable t) {
                try {
                    customerResponse.postValue(null);
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });

        return customerResponse;
    }


    public LiveData<ClsProductCategoryResponse> getProductCategory() {

        final MutableLiveData<ClsProductCategoryResponse> OrdersResponseList = new MutableLiveData<>();
        InterfaceItemInfo interfaceOwner = ApiClient.getRetrofitInstance().create(InterfaceItemInfo.class);
        Call<ClsProductCategoryResponse> call = interfaceOwner.getCategory();


        call.enqueue(new Callback<ClsProductCategoryResponse>() {
            @Override
            public void onResponse(Call<ClsProductCategoryResponse> call, Response<ClsProductCategoryResponse> response) {

                if (response.body() != null && response.code() == 200) {

                    OrdersResponseList.setValue(response.body());


                }
            }

            @Override
            public void onFailure(Call<ClsProductCategoryResponse> call, Throwable t) {

                try {
                    OrdersResponseList.setValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return OrdersResponseList;
    }


    public LiveData<ClsProductResponse> getProduct(int productId) {

        final MutableLiveData<ClsProductResponse> OrdersResponseList = new MutableLiveData<>();
        InterfaceItemInfo interfaceItemInfo = ApiClient.getRetrofitInstance().create(InterfaceItemInfo.class);
        Call<ClsProductResponse> call = interfaceItemInfo.getProduct(productId);


        call.enqueue(new Callback<ClsProductResponse>() {
            @Override
            public void onResponse(Call<ClsProductResponse> call, Response<ClsProductResponse> response) {

                if (response.body() != null && response.code() == 200) {

                    OrdersResponseList.setValue(response.body());


                }
            }

            @Override
            public void onFailure(Call<ClsProductResponse> call, Throwable t) {

                try {
                    OrdersResponseList.setValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return OrdersResponseList;
    }

    public LiveData<ClsCommonResponse> editOrder(String flag, String order_id, String order_data, String total) {

        final MutableLiveData<ClsCommonResponse> orderResponse = new MutableLiveData<>();
        InterfaceOwner interfaceOwner = ApiClient.getRetrofitInstance().create(InterfaceOwner.class);


        Call<ClsCommonResponse> call = interfaceOwner.editOrder(flag, order_id, order_data, total);


        call.enqueue(new Callback<ClsCommonResponse>() {
            @Override
            public void onResponse(Call<ClsCommonResponse> call, Response<ClsCommonResponse>
                    response) {

                if (response.body() != null) {

                    orderResponse.postValue(response.body());


                } else {
                    Toast.makeText(context, R.string.somethingWrong, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClsCommonResponse> call, Throwable t) {
                try {
                    orderResponse.postValue(null);

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
        return orderResponse;
    }


    public LiveData<ClsNormalResponse> addProduct(String c_id, String product_name, String unit_name, String unit_price, String product_description, String stock *//*AddProduct addProduct*//*) {

        final MutableLiveData<ClsNormalResponse> orderResponse = new MutableLiveData<>();
        InterfaceOwner interfaceOwner = ApiClient.getRetrofitInstance().create(InterfaceOwner.class);


        Call<ClsNormalResponse> call = interfaceOwner.addProduct(c_id, product_name, unit_name,unit_price,product_description,stock);
       // Call<ClsNormalResponse> call = interfaceOwner.addProduct(addProduct);

        call.enqueue(new Callback<ClsNormalResponse>() {
            @Override
            public void onResponse(Call<ClsNormalResponse> call, Response<ClsNormalResponse>
                    response) {

                if (response.body() != null) {
                    orderResponse.postValue(response.body());
                  //  Log.d("Check", "onResponse: if "+new Gson().toJson(response));
                } else {
                    Toast.makeText(context, R.string.somethingWrong, Toast.LENGTH_SHORT).show();
                    try {
                     //   Log.d("Check", "onResponse: else try"+new Gson().toJson(response));
                    }catch (Exception e){
                        e.printStackTrace();
                        Log.d("Check", "onResponse: else catch"+e.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ClsNormalResponse> call, Throwable t) {
                try {
                    orderResponse.postValue(null);
                    Log.d("Check", "onFailure: try "+t.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("Check", "onFailure: catch"+t.toString());
                }
            }
        });
        return orderResponse;
    }

    public LiveData<ClsNormalResponse> updateProduct(String p_id, String c_id, String product_name, String unit_name, String product_description, String stock) {// String unit_price,unit_price,

        final MutableLiveData<ClsNormalResponse> orderResponse = new MutableLiveData<>();
        InterfaceOwner interfaceOwner = ApiClient.getRetrofitInstance().create(InterfaceOwner.class);


        Call<ClsNormalResponse> call = interfaceOwner.updateProduct(p_id, c_id, product_name, unit_name,product_description,stock);


        call.enqueue(new Callback<ClsNormalResponse>() {
            @Override
            public void onResponse(Call<ClsNormalResponse> call, Response<ClsNormalResponse>
                    response) {

                if (response.body() != null) {

                    orderResponse.postValue(response.body());


                } else {
                    Toast.makeText(context, R.string.somethingWrong, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClsNormalResponse> call, Throwable t) {
                try {
                    orderResponse.postValue(null);

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
        return orderResponse;
    }

    public LiveData<ClsNormalResponse> addCategory(String c_name, String c_description) {

        final MutableLiveData<ClsNormalResponse> orderResponse = new MutableLiveData<>();
        InterfaceOwner interfaceOwner = ApiClient.getRetrofitInstance().create(InterfaceOwner.class);


        Call<ClsNormalResponse> call = interfaceOwner.addCategory(c_name, c_description);


        call.enqueue(new Callback<ClsNormalResponse>() {
            @Override
            public void onResponse(Call<ClsNormalResponse> call, Response<ClsNormalResponse>
                    response) {

                if (response.body() != null) {

                    orderResponse.postValue(response.body());


                } else {
                    Toast.makeText(context, R.string.somethingWrong, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClsNormalResponse> call, Throwable t) {
                try {
                    orderResponse.postValue(null);

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
        return orderResponse;
    }


    public LiveData<ClsNormalResponse> editCategory(String c_id, String c_name, String c_description) {

        final MutableLiveData<ClsNormalResponse> orderResponse = new MutableLiveData<>();
        InterfaceOwner interfaceOwner = ApiClient.getRetrofitInstance().create(InterfaceOwner.class);


        Call<ClsNormalResponse> call = interfaceOwner.editCategory(c_id,c_name, c_description);


        call.enqueue(new Callback<ClsNormalResponse>() {
            @Override
            public void onResponse(Call<ClsNormalResponse> call, Response<ClsNormalResponse>
                    response) {

                if (response.body() != null) {

                    orderResponse.postValue(response.body());


                } else {
                    Toast.makeText(context, R.string.somethingWrong, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClsNormalResponse> call, Throwable t) {
                try {
                    orderResponse.postValue(null);

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
        return orderResponse;
    }


    public LiveData<ClsNormalResponse> deleteProduct(String product_id) {

        final MutableLiveData<ClsNormalResponse> orderResponse = new MutableLiveData<>();
        InterfaceOwner interfaceOwner = ApiClient.getRetrofitInstance().create(InterfaceOwner.class);


        Call<ClsNormalResponse> call = interfaceOwner.deleteProduct(product_id);


        call.enqueue(new Callback<ClsNormalResponse>() {
            @Override
            public void onResponse(Call<ClsNormalResponse> call, Response<ClsNormalResponse>
                    response) {

                if (response.body() != null) {

                    orderResponse.postValue(response.body());


                } else {
                    Toast.makeText(context, R.string.somethingWrong, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClsNormalResponse> call, Throwable t) {
                try {
                    orderResponse.postValue(null);

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
        return orderResponse;
    }

    public LiveData<ClsNormalResponse> deleteCategory(String c_id) {

        final MutableLiveData<ClsNormalResponse> orderResponse = new MutableLiveData<>();
        InterfaceOwner interfaceOwner = ApiClient.getRetrofitInstance().create(InterfaceOwner.class);


        Call<ClsNormalResponse> call = interfaceOwner.deleteCategory(c_id);


        call.enqueue(new Callback<ClsNormalResponse>() {
            @Override
            public void onResponse(Call<ClsNormalResponse> call, Response<ClsNormalResponse>
                    response) {

                if (response.body() != null) {

                    orderResponse.postValue(response.body());


                } else {
                    Toast.makeText(context, R.string.somethingWrong, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClsNormalResponse> call, Throwable t) {
                try {
                    orderResponse.postValue(null);

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
        return orderResponse;
    }

    public LiveData<ClsCommonResponse> changePassword(String flag, String password, String id) {

        final MutableLiveData<ClsCommonResponse> customerResponse = new MutableLiveData<>();
        InterfaceCustomer interfaceCustomer = ApiClient.getRetrofitInstance().create(InterfaceCustomer.class);


        Call<ClsCommonResponse> call = interfaceCustomer.changePassword(flag, password, id);


        call.enqueue(new Callback<ClsCommonResponse>() {
            @Override
            public void onResponse(Call<ClsCommonResponse> call, Response<ClsCommonResponse>
                    response) {

                if (response.body() != null) {
                    customerResponse.postValue(response.body());
                } else {
                    Toast.makeText(context, R.string.somethingWrong, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClsCommonResponse> call, Throwable t) {
                try {
                    customerResponse.postValue(null);

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });

        return customerResponse;
    }
    public LiveData<ClsNormalResponse> deleteCompleteOrder() {

        final MutableLiveData<ClsNormalResponse> orderResponse = new MutableLiveData<>();
        InterfaceOwner interfaceOwner = ApiClient.getRetrofitInstance().create(InterfaceOwner.class);


        Call<ClsNormalResponse> call = interfaceOwner.deleteCompleteOrders();


        call.enqueue(new Callback<ClsNormalResponse>() {
            @Override
            public void onResponse(Call<ClsNormalResponse> call, Response<ClsNormalResponse>
                    response) {

                if (response.body() != null) {

                    orderResponse.postValue(response.body());


                } else {
                    Toast.makeText(context, R.string.somethingWrong, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClsNormalResponse> call, Throwable t) {
                try {
                    orderResponse.postValue(null);

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
        return orderResponse;
    }


    public LiveData<ClsProductResponse> getAllProduct() {
        final MutableLiveData<ClsProductResponse> OrdersResponseList = new MutableLiveData<>();
        InterfaceItemInfo interfaceItemInfo = ApiClient.getRetrofitInstance().create(InterfaceItemInfo.class);
        Call<ClsProductResponse> call = interfaceItemInfo.getAllProduct();


        call.enqueue(new Callback<ClsProductResponse>() {
            @Override
            public void onResponse(Call<ClsProductResponse> call, Response<ClsProductResponse> response) {

                if (response.body() != null && response.code() == 200) {

                    OrdersResponseList.setValue(response.body());


                }
            }

            @Override
            public void onFailure(Call<ClsProductResponse> call, Throwable t) {

                try {
                    OrdersResponseList.setValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return OrdersResponseList;
    }

    public LiveData<ClsAddPriceResponse> addPrice(String item_id, String qty, String price, String unit_type) {

        final MutableLiveData<ClsAddPriceResponse> orderResponse = new MutableLiveData<>();
        InterfaceOwner interfaceOwner = ApiClient.getRetrofitInstance().create(InterfaceOwner.class);


        Call<ClsAddPriceResponse> call = interfaceOwner.addPrice(item_id,qty,price,unit_type);
        // Call<ClsNormalResponse> call = interfaceOwner.addProduct(addProduct);

        call.enqueue(new Callback<ClsAddPriceResponse>() {
            @Override
            public void onResponse(Call<ClsAddPriceResponse> call, Response<ClsAddPriceResponse>
                    response) {

                if (response.body() != null) {
                    orderResponse.postValue(response.body());
                    //  Log.d("Check", "onResponse: if "+new Gson().toJson(response));
                } else {
                    Toast.makeText(context, R.string.somethingWrong, Toast.LENGTH_SHORT).show();
                    try {
                        //   Log.d("Check", "onResponse: else try"+new Gson().toJson(response));
                    }catch (Exception e){
                        e.printStackTrace();
                        Log.d("Check", "onResponse: else catch"+e.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ClsAddPriceResponse> call, Throwable t) {
                try {
                    orderResponse.postValue(null);
                    Log.d("Check", "onFailure: try "+t.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("Check", "onFailure: catch"+t.toString());
                }
            }
        });
        return orderResponse;
    }

    public LiveData<ClsNormalResponse> editPrice(String item_id, String qty, String price, String unit_type, String p_id) {

        final MutableLiveData<ClsNormalResponse> orderResponse = new MutableLiveData<>();
        InterfaceOwner interfaceOwner = ApiClient.getRetrofitInstance().create(InterfaceOwner.class);


        Call<ClsNormalResponse> call = interfaceOwner.editPrice(item_id,qty,price,unit_type,p_id);
        // Call<ClsNormalResponse> call = interfaceOwner.addProduct(addProduct);

        call.enqueue(new Callback<ClsNormalResponse>() {
            @Override
            public void onResponse(Call<ClsNormalResponse> call, Response<ClsNormalResponse>
                    response) {

                if (response.body() != null) {
                    orderResponse.postValue(response.body());
                    //  Log.d("Check", "onResponse: if "+new Gson().toJson(response));
                } else {
                    Toast.makeText(context, R.string.somethingWrong, Toast.LENGTH_SHORT).show();
                    try {
                        //   Log.d("Check", "onResponse: else try"+new Gson().toJson(response));
                    }catch (Exception e){
                        e.printStackTrace();
                        Log.d("Check", "onResponse: else catch"+e.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ClsNormalResponse> call, Throwable t) {
                try {
                    orderResponse.postValue(null);
                    Log.d("Check", "onFailure: try "+t.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("Check", "onFailure: catch"+t.toString());
                }
            }
        });
        return orderResponse;
    }


    public LiveData<ClsProductByIdResponse> getProductId(int p_id) {

        final MutableLiveData<ClsProductByIdResponse> orderResponse = new MutableLiveData<>();
        InterfaceOwner interfaceOwner = ApiClient.getRetrofitInstance().create(InterfaceOwner.class);


        Call<ClsProductByIdResponse> call = interfaceOwner.getProductById(p_id);


        try {
            call.enqueue(new Callback<ClsProductByIdResponse>() {
                @Override
                public void onResponse(Call<ClsProductByIdResponse> call, Response<ClsProductByIdResponse> response) {

                    if (response.body() != null) {

                        orderResponse.postValue(response.body());
                        Log.d("Check", "onResponse: s");

                    } else {
                        Toast.makeText(context, R.string.somethingWrong, Toast.LENGTH_SHORT).show();
                        try {
                              Log.d("Check", "onResponse: else try"+new Gson().toJson(response.body().getStatus()));
                        }catch (Exception e){
                            e.printStackTrace();
                            Log.d("Check", "onResponse: else catch"+e.toString());
                        }
                    }
                }

                @Override
                public void onFailure(Call<ClsProductByIdResponse> call, Throwable t) {
                    try {
                        orderResponse.postValue(null);
                        Log.d("Check", "onFailure:  try"+t.toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("Check", "onFailure:  catch"+e.toString());

                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Check", "onFailure:  catch"+e.toString());
        }
        Log.d("Check", "onResponse: else try"+new Gson().toJson(orderResponse));
        return orderResponse;
    }

    public LiveData<ClsNormalResponse> deletePrice(int price_id) {

        final MutableLiveData<ClsNormalResponse> orderResponse = new MutableLiveData<>();
        InterfaceOwner interfaceOwner = ApiClient.getRetrofitInstance().create(InterfaceOwner.class);


        Call<ClsNormalResponse> call = interfaceOwner.deletePrice(price_id);


        try {
            call.enqueue(new Callback<ClsNormalResponse>() {
                @Override
                public void onResponse(Call<ClsNormalResponse> call, Response<ClsNormalResponse> response) {

                    if (response.body() != null) {

                        orderResponse.postValue(response.body());
                        Log.d("Check", "onResponse: s");

                    } else {
                        Toast.makeText(context, R.string.somethingWrong, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ClsNormalResponse> call, Throwable t) {
                    try {
                        orderResponse.postValue(null);
                        Log.d("Check", "onFailure:  try"+t.toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("Check", "onFailure:  catch"+e.toString());

                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Check", "onFailure:  catch"+e.toString());
        }
        Log.d("Check", "onResponse: else try"+new Gson().toJson(orderResponse));
        return orderResponse;
    }
*/
}
